package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {



    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "select * from ogrenci as o inner join islem as i on o.ogrno=i.ogrno";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT * FROM ogrenci WHERE ogrno NOT IN (SELECT ogrno FROM islem)";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "select sinif,count(i.islemno) from ogrenci as o inner join islem as i on o.ogrno=i.ogrno where sinif='10A' or sinif='10B' group by sinif";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "select count(ogrno) from ogrenci";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT COUNT(DISTINCT ad) FROM ogrenci;";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "select ad,count(ad) from ogrenci group by ad";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();

//Her sınıftaki öğrenci sayısını bulunuz.
    String QUESTION_8 = "select sinif,count(ogrno)from ogrenci group by sinif";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();
//Her öğrencinin ad soyad karşılığında okuduğu kitap sayısını getiriniz.
    String QUESTION_9 = "select o.ad,o.soyad,count(i.islemno) from ogrenci as o inner join islem as i on o.ogrno=i.ogrno group by o.ad,o.soyad";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
