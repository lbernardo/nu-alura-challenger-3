(ns common.kafka.kafka
  (:require [clojure.data.json :as json])
  (:import (java.time Duration)
           (org.apache.kafka.clients.consumer KafkaConsumer)
           (org.apache.kafka.clients.producer KafkaProducer ProducerRecord)
           (org.apache.kafka.common.errors TopicExistsException)
           (org.apache.kafka.clients.admin AdminClient NewTopic)))


(defn- create-topic! [topic partitions replication cloud-config]
  (let [ac (AdminClient/create cloud-config)]
    (try
      (.createTopics ac [(NewTopic. topic partitions replication)])
      (catch TopicExistsException e nil)
      (finally
        (.close ac)))))

(defn consumer! [config topic callback]
  (with-open [consumer (KafkaConsumer. config)]
    (.subscribe consumer [topic])
    (loop [tc 0
           records []]
      (let [new-tc (reduce
                     (fn [_ record]
                       (let [value (.value record)]
                         (callback value)))
                     tc
                     records)]
        (recur new-tc
               (seq (.poll consumer (Duration/ofSeconds 1))))))))


(defn producer! [config topic message]
  (with-open [producer (KafkaProducer. config)]
    (create-topic! topic 1 3 config)
    (.send producer (ProducerRecord. topic "EVENT" message))
    (.flush producer)))

