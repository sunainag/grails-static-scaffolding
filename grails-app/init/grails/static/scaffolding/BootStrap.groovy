package grails.static.scaffolding

import groovy.transform.CompileStatic

@CompileStatic
class BootStrap {

	SongService songService
	PlaylistService playlistService

    def init = { servletContext ->

    	Song testSong1 = songService.save('song1')
    	Song testSong2 = songService.save('song2')
    	Song testSong3 = songService.save('song3')
    	playlistService.save("playlist1", testSong1).save()
    	playlistService.save("playlist1", testSong2).save()
    	playlistService.save("playlist1", testSong3).save()

    }
    def destroy = {
    }
}
