(ns common.kafka.config
  (:require [clojure.data.json :as json]
            [clojure.java.io :as jio])
  (:import
    (java.util Properties)
    (org.apache.kafka.clients.consumer ConsumerConfig)
    (org.apache.kafka.clients.producer ProducerConfig)))


(def data-file (jio/resource "app.config"))

(defn build-properties-consumer []
  (with-open [config (jio/reader data-file)]
    (doto (Properties.)
      (.putAll {ConsumerConfig/GROUP_ID_CONFIG,                "clojure_example_group"
                ConsumerConfig/KEY_DESERIALIZER_CLASS_CONFIG   "org.apache.kafka.common.serialization.StringDeserializer"
                ConsumerConfig/VALUE_DESERIALIZER_CLASS_CONFIG "org.apache.kafka.common.serialization.StringDeserializer"})
      (.load config))))

(defn build-properties-producer []
  (with-open [config (jio/reader data-file)]
    (doto (Properties.)
      (.putAll {ProducerConfig/KEY_SERIALIZER_CLASS_CONFIG   "org.apache.kafka.common.serialization.StringSerializer"
                ProducerConfig/VALUE_SERIALIZER_CLASS_CONFIG "org.apache.kafka.common.serialization.StringSerializer"})
      (.load config))))