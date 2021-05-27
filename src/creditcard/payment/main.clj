(ns creditcard.payment.main
  (:require [common.kafka.kafka :as c.kafka]
            [common.kafka.config :as c.config]
            [creditcard.payment.logic :as n.logic]
            [clojure.data.json :as json]))


(defn- call-message [value]
  (let [content (json/read-str value)]
    (n.logic/payment content)))

(defn -main [& args]
  (c.kafka/consumer! (c.config/build-properties-consumer) "CREDITCARD_PAYMENT" call-message))