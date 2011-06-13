/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package geofx

import groovy.json.*

class GeocodingParser {
    static def GEOCODE_JSON_URL = "http://maps.googleapis.com/maps/api/geocode/json"
    static def GEOCODE_XML_URL = "http://maps.googleapis.com/maps/api/geocode/xml"
    
    static def getGeocodeForAddress(address){
        def queryBuilder = []
        queryBuilder << "address=${URLEncoder.encode(address)}"
        queryBuilder << "sensor=false"
        def queryString = queryBuilder.join("&")
        def requestUrl = GEOCODE_JSON_URL+"?${queryString}"
        def payload = new URL(requestUrl).text
        def jsonSlurper = new JsonSlurper()
        def doc = jsonSlurper.parseText(payload)
        def geocode = new Geocode()
        geocode.latitude = doc.results.geometry.location.lat.join("")
        geocode.longitude = doc.results.geometry.location.lng.join("")
        geocode.locationType = doc.results.geometry.location_type.join("")
        return geocode
    }
}

