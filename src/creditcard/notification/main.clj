(ns creditcard.notification.main
  (:require [common.kafka.kafka :as c.kafka]
            [common.kafka.config :as c.config]
            [creditcard.notification.logic :as n.logic]
            [clojure.data.json :as json]))


(defn- call-message [value]
  (let [content (json/read-str value)]
    (n.logic/print-notification content)))

(defn -main [& args]
  (c.kafka/consumer! (c.config/build-properties-consumer) "CREDITCARD_NOTIFICATION" call-message))