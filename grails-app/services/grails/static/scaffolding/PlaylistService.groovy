package grails.static.scaffolding

import grails.gorm.services.Service

@Service(Playlist)
interface PlaylistService {

    Playlist get(Serializable id)

    List<Playlist> list(Map args)

    Long count()

    void delete(Serializable id)

    Playlist save(String name, Song song)

}