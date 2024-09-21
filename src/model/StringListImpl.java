package model;

import exeption.*;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private final String[] list;
    private int maxNumber;

    public StringListImpl() {
        list = new String[10];
    }

    public StringListImpl(int size) {
        list = new String[size];
    }


    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(String item) {
        validateItem(item);
        validateSize();
        list[maxNumber++] = item;
        /*for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = item;
                break;
            } else {
                throw new NoFreeSpaceException();
            }
         */
        return item;

    }

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(int index, String item) {
        /*if (list[index] != null) {
            throw new NoFreeSpaceException();
        }
        if (index > list.length) {
            throw new ArrayExceedsException();
        }
         */
        validateSize();
        validateItem(item);
        validateIndex(index);

        if (index == maxNumber) {
            list[maxNumber++] = item;
            return item;
        }
        System.arraycopy(list, index,list,index + 1,maxNumber-index);
            list[index] = item;
            maxNumber++;
            return item;
    }


    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс больше
    // фактического количества элементов
    // или выходит за пределы массива.
    @Override
    public String set(int index, String item) {
        /*validateIndex(index);
        validateItem(item);
         */
        if (index > list.length || index < 0 && item==null) {
            throw new ArrayExceedsException();
        }
        list[index] = item;
        return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(String item) {
        /*for (int i = 0; i < list.length; i++) {
            if (item.equals(list[i])) {
                list[i] = item;
            } else {
                throw new NotFoundException();
            }

        }
         */
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new NotFoundException();
        }
        if (index != maxNumber) {
            System.arraycopy(list, index + 1, list, index, maxNumber - index);
        }
        maxNumber--;
        return item;
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(int index) {
        /*for (int i = 0; i < list.length; i++) {
            if (list[i].equals(list[index])) {
                list[i] = null;
            }
            if (list[i] == null) {
                throw new NotFoundException();
            }
            break;
        }

         */
        validateIndex(index);
        String item = list[index];

        if (index != maxNumber) {
            System.arraycopy(list, index + 1, list, index, maxNumber - index);
        }
        maxNumber--;
        return item;

    }


    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(String item) {
        //return indexOf(item) != -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(item)) {
                return true;
            }

        }
        return false;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(String item) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(String item) {
        for (int i = list.length -1 ; i >= 0 ; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public String get(int index) {
        if (list.length < index || index < 0) {
            throw new ArrayExceedsException();
        }
        return list[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(),otherList.toArray());
    }

    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        return list.length;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        return maxNumber == 0;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        maxNumber = 0;
        /*
        for (int i = 0; i < list.length; i++) {
            list[i] = null;
        }

         */
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public String[] toArray() {
        return Arrays.copyOf(list , maxNumber);
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (maxNumber == list.length) {
            throw new IsFullExceptionException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= maxNumber) {
            throw new InvalidIndexException();

        }
    }
}




