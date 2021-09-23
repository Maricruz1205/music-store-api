/* 
* This program is free software: you can redistribute it and/or modify  
* it under the terms of the GNU General Public License as published by  
* the Free Software Foundation, version 3.
*
* This program is distributed in the hope that it will be useful, but 
* WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
* General Public License for more details.
*
* Nombre de archivo: MusicStoreController.java
* Autor: maricjim
* Fecha de creación: 23 sep. 2021
*/
package com.music.store.mx.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.music.store.mx.application.dto.AlbumDto;
import com.music.store.mx.application.dto.SongDto;

import com.music.store.mx.application.service.MusicStoreService;
import com.music.store.mx.model.Album;
import com.music.store.mx.model.Song;

/**
 * The Class MusicStoreController.
 */
@RestController
public class MusicStoreController {

  /** The music store service. */
  private MusicStoreService musicStoreService;

  /**
   * Instantiates a new music store controller.
   *
   * @param musicStoreService the music store service
   */
  public MusicStoreController(MusicStoreService musicStoreService) {
    this.musicStoreService = musicStoreService;
  }

  /**
   * Gets the albums.
   *
   * @return the albums
   */
  // Obtiene todos los albums
  @GetMapping(value = "/api/v1/albums", produces = "application/json")
  public ResponseEntity<List<AlbumDto>> getAlbums() {

    return new ResponseEntity<>(musicStoreService.retrieveAlbums(), HttpStatus.OK);
  }

  /**
   * Creat album.
   *
   * @param album the album
   * @return the response entity
   */
  // Crea un album
  @PostMapping(value = "/api/v1/albums")
  public ResponseEntity<Album> creatAlbum(@RequestBody AlbumDto album) {
    this.musicStoreService.createAlbum(album);
    return null;
  }

  /**
   * Delete album.
   *
   * @param albumId the album id
   * @return the response entity
   */
  // Borra un album por id
  @DeleteMapping(value = "/api/v1/albums/{albumId}")
  public ResponseEntity<Void> deleteAlbum(@PathVariable("albumId") Long albumId) {
    musicStoreService.deleteAlbum(albumId);
    return ResponseEntity.ok(null);
  }

  /**
   * Update album.
   *
   * @param albumDto the album dto
   * @return the response entity
   */
  // Actualiza un album por Id
  @PutMapping(value = "/api/v1/albums/{albumId}")
  public ResponseEntity<Album> updateAlbum(@RequestBody AlbumDto albumDto) {
    this.musicStoreService.updateAlbum(albumDto);
    return null;

  }

  /**
   * Gets the album by id.
   *
   * @param albumId the album id
   * @return the album by id
   */
  // Obtiene los datos de un album por Id
  @GetMapping(value = "/api/v1/{albumId}/album", produces = "application/json")
  public ResponseEntity<AlbumDto> getAlbumById(@PathVariable(name = "albumId") Long albumId) {
    return new ResponseEntity<>(musicStoreService.findAlbumById(albumId), HttpStatus.OK);
  }

  /**
   * Delete albums.
   *
   * @return the response entity
   */
  // Borra todos los albums
  @DeleteMapping(value = "/api/v1/albums")
  public ResponseEntity<Void> deleteAlbums() {
    musicStoreService.deleteAlbums();;
    return ResponseEntity.ok(null);
  }

  /**
   * Update albums.
   *
   * @param albumDto the album dto
   * @return the response entity
   */
  // Edita la etiqueta label: Disquera
  @PutMapping(value = "/api/v1/albums")
  public ResponseEntity updateAlbums(@RequestBody AlbumDto albumDto) {
    if (musicStoreService.updateAlbums(albumDto.getLabel())) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // **************************************************************************************

  /**
   * Gets the songs.
   *
   * @return the songs
   */
  // Obtiene todas las canciones
  @GetMapping(value = "/api/v1/songs", produces = "application/json")
  public ResponseEntity<List<SongDto>> getSongs() {
    return new ResponseEntity<>(musicStoreService.retriveSongs(), HttpStatus.OK);
  }

  /**
   * Creat song.
   *
   * @param songDto the song dto
   * @return the response entity
   */
  // Crea una cancion
  @PostMapping(value = "/api/v1/songs")
  public ResponseEntity<Song> creatSong(@RequestBody SongDto songDto) {
    this.musicStoreService.createSong(songDto);
    return null;
  }

  /**
   * Update song.
   *
   * @param songDto the song dto
   * @return the response entity
   */
  // Actualiza una cancion por Id
  @PutMapping(value = "/api/v1/songs/{songId}")
  public ResponseEntity<Song> updateSong(@RequestBody SongDto songDto) {
    this.musicStoreService.updateSong(songDto);
    return null;

  }

  /**
   * Delete song.
   *
   * @param songId the song id
   * @return the response entity
   */
  // Borra una cancion por Id
  @DeleteMapping(value = "/api/v1/song/{songId}")
  public ResponseEntity<Void> deleteSong(@PathVariable("songId") Long songId) {
    musicStoreService.deleteSong(songId);
    return ResponseEntity.ok(null);
  }

  /**
   * Gets the songs.
   *
   * @param albumId the album id
   * @return the songs
   */
  // Obtiene todas las cancion por id de album
  @GetMapping(value = "/api/v1/{albumId}/songs", produces = "application/json")
  public ResponseEntity<List<SongDto>> getSongs(@PathVariable(name = "albumId") Long albumId) {
    return new ResponseEntity<>(musicStoreService.retriveSongs(albumId), HttpStatus.OK);
  }

  /**
   * Gets the song by id.
   *
   * @param albumId the album id
   * @param songId the song id
   * @return the song by id
   */
  // Obtiene una cancion por id y id de album
  @GetMapping(value = "/api/v1/albums/{albumId}/songs/{songId}", produces = "application/json")
  public ResponseEntity<SongDto> getSongById(@PathVariable("albumId") Long albumId,
      @PathVariable("songId") Long songId) {
    return musicStoreService.getSongByIdAndAlbumId(songId, albumId)
        .map(song -> new ResponseEntity<>(song, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }


  /**
   * Delete song.
   *
   * @param albumId the album id
   * @param songId the song id
   * @return the response entity
   */
  // Elimina una cancion por id de album y id de cancion
  @DeleteMapping(value = "/api/v1/albums/{albumId}/songs/{songId}")
  public ResponseEntity deleteSong(@PathVariable("albumId") Long albumId,
      @PathVariable("songId") Long songId) {
    if (musicStoreService.deleteSongId(songId, albumId)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
