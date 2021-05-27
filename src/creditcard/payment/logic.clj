(ns creditcard.payment.logic
  (:require [clojure.data.json :as json]
            [common.kafka.kafka :as c.kafka]
            [common.kafka.config :as c.config]))

(defn payment [content]
  (let [transaction (get content "transaction")
        value (get content "value")]
    (println "Realizando cobrança de" transaction "no valor R$" value))
  (c.kafka/producer! (c.config/build-properties-producer) "CREDITCARD_NOTIFICATION" (json/write-str {:message "Pagamento realizado!"})))
