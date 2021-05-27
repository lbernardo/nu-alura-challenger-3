(ns creditcard.antifraud.main
  (:require [common.kafka.kafka :as c.kafka]
            [common.kafka.config :as c.config]
            [creditcard.antifraud.logic :as a.logic]
            [clojure.data.json :as json]))


(defn- call-message [value]
  (let [content (json/read-str value)]
    (a.logic/check-antifraud content)))

(defn -main [& args]
  (c.kafka/consumer! (c.config/build-properties-consumer) "CREDITCARD_ANTIFRAUD" call-message))