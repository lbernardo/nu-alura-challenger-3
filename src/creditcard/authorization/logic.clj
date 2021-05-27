(ns creditcard.authorization.logic
  (:require
    [clojure.data.json :as json]
    [common.kafka.kafka :as c.kafka]
            [common.kafka.config :as c.config])
  (:import (java.util UUID)))


(defn authorization [content]
  (let [transaction (.toString (UUID/randomUUID))
        value (get content "value")]
    (println "Autorização de R$" value "para transação" transaction)
    (c.kafka/producer! (c.config/build-properties-producer) "CREDITCARD_ANTIFRAUD" (json/write-str {:action "IS_FRAUD" :transaction transaction :value value}))))

(defn cancel-authorization! [content]
  (let [transaction (get content "transaction")
        value (get content "value")]
    (println "Cancelando autorização " transaction "no valor de R$" value)))