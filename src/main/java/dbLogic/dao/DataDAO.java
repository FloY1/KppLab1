package dbLogic.dao;


import DataElements.DataFile;

import java.util.List;
/** Интерфейс позволяющий рабоду считывания , записи  данных класса DataFile
 * @author  artem.smolonskiy
 * @version 1.0
 */
public interface DataDAO {

     /**
      * Вывод
      * @param dataFile что выводим
      */
     void add(DataFile dataFile);

     /**
      * Считываие
      * @return Список всех данных
      */
     List<DataFile> getAll() ;


     /**
      * Обновление
      * @param dataFile новый файл
      */
     void update(DataFile dataFile) ;

     /**
      * Удаление
      * @param dataFile кдаляймый файл
      */
     void remove(DataFile dataFile) ;


}

