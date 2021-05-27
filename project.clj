(defproject creditcard "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "0.2.6"]
                 [org.apache.kafka/kafka-clients "2.1.0"]]
  :resource-paths ["resources"]
  :aliases {
            "authorization" ["run" "-m" "creditcard.authorization.main"]
            "antifraud" ["run" "-m" "creditcard.antifraud.main"]
            "notification" ["run" "-m" "creditcard.notification.main"]
            "payment" ["run" "-m" "creditcard.payment.main"]
            "purchase" ["run" "-m" "creditcard.purchase.main"]
            }
  :repl-options {:init-ns creditcard.core})
