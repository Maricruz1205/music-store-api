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
* Nombre de archivo: SongsRepository.java
* Autor: maricjim
* Fecha de creación: 23 sep. 2021
*/
package com.music.store.mx.application.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.music.store.mx.model.Song;

/**
 * The Interface SongsRepository.
 */
public interface SongsRepository extends JpaRepository<Song, Long> {

  /**
   * Find all.
   *
   * @return the list
   */
  List<Song> findAll();

  /**
   * Find by author.
   *
   * @param author the author
   * @return the list
   */
  List<Song> findByAuthor(String author);

  /**
   * Find by album id.
   *
   * @param albumId the album id
   * @return the list
   */
  List<Song> findByAlbumId(Long albumId);


  /**
   * Find by id and album id.
   *
   * @param songId the song id
   * @param albumId the album id
   * @return the optional
   */
  @Query(value = "SELECT  * FROM Cancion WHERE idCancion = ? && idAlbum = ? LIMIT 1",
      nativeQuery = true)
  Optional<Song> findByIdAndAlbumId(Long songId, Long albumId);

  /**
   * Delete song.
   *
   * @param songId the song id
   * @param albumId the album id
   */
  @Transactional
  @Modifying
  @Query(value = "DELETE FROM Cancion WHERE idCancion = ? && idAlbum = ?", nativeQuery = true)
  void deleteSong(Long songId, Long albumId);


}
