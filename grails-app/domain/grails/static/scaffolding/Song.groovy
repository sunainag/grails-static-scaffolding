package grails.static.scaffolding

import groovy.transform.ToString

@ToString
class Song {

	String title

    static constraints = {
    	title nullable: false
    	title unique: true
    }
}
