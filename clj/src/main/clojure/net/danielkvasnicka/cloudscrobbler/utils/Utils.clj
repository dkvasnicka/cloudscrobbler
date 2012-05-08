(ns net.danielkvasnicka.cloudscrobbler.utils.Utils
	(:gen-class
      :methods [#^{:static true} [getOnlyTracksFromMixSections [java.util.Collection] java.util.Collection]]))

(defn -getOnlyTracksFromMixSections [sections]
  (map #(.getTrack %) (filter #(.isTrack %) sections)))
