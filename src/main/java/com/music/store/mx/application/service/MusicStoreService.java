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
* Nombre de archivo: MusicStoreService.java
* Autor: maricjim
* Fecha de creación: 23 sep. 2021
*/
package com.music.store.mx.application.service;

import java.util.List;
import java.util.Optional;

import com.music.store.mx.application.dto.AlbumDto;
import com.music.store.mx.application.dto.SongDto;

/**
 * The Interface MusicStoreService.
 */
public interface MusicStoreService {
  
  /**
   * Retrieve albums.
   *
   * @return the list
   */
  List<AlbumDto> retrieveAlbums();

  /**
   * Retrive songs.
   *
   * @return the list
   */
  List<SongDto> retriveSongs();

  /**
   * Retrive songs.
   *
   * @param albumId the album id
   * @return the list
   */
  List<SongDto> retriveSongs(Long albumId);

  /**
   * Find album by id.
   *
   * @param albumId the album id
   * @return the album dto
   */
  AlbumDto findAlbumById(Long albumId);

  /**
   * Creates the album.
   *
   * @param albumDto the album dto
   */
  public void createAlbum(AlbumDto albumDto);

  /**
   * Delete album.
   *
   * @param albumId the album id
   */
  public void deleteAlbum(Long albumId);

  /**
   * Update album.
   *
   * @param albumDto the album dto
   */
  public void updateAlbum(AlbumDto albumDto);

  /**
   * Creates the song.
   *
   * @param songDto the song dto
   */
  public void createSong(SongDto songDto);

  /**
   * Delete song.
   *
   * @param songId the song id
   */
  public void deleteSong(Long songId);

  /**
   * Update song.
   *
   * @param songDto the song dto
   */
  public void updateSong(SongDto songDto);

  /**
   * Delete songs.
   *
   * @param albumId the album id
   */
  public void deleteSongs(Long albumId);

  /**
   * Update albums.
   *
   * @param label the label
   * @return the boolean
   */
  Boolean updateAlbums(String label);

  /**
   * Delete albums.
   */
  public void deleteAlbums();

  /**
   * Gets the song by id and album id.
   *
   * @param songId the song id
   * @param albumId the album id
   * @return the song by id and album id
   */
  Optional<SongDto> getSongByIdAndAlbumId(Long songId, Long albumId);

  /**
   * Delete song id.
   *
   * @param songId the song id
   * @param albumId the album id
   * @return the boolean
   */
  Boolean deleteSongId(Long songId, Long albumId);

}
