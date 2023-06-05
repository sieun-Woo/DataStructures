package _01_ArrayList;

import Interface.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10; // 최소(기본) 용적 크기
    private static final Object[] EMPTY_ARRAY = {}; // 빈 배열

    private int size; // 요소 개수

    Object[] elementData; // 요소를 담을 배열

    // 생성자1 (초기 공간 할당 X)
    public ArrayList() {
        this.elementData = EMPTY_ARRAY;
        this.size = 0;
    }


    // 생성자2 (초기 공간 할당 O)
    public ArrayList(int capacity) {
        this.elementData = new Object[capacity];
        this.size = 0;
    }

    /**
     * elementData 배열에서 지정된 요소를 반환
     * Object 타입에서 E 타입으로 캐스팅 후 반환
     *
     * @param index
     * @return 지정된 위치의 요소
     */

    private E elementData(int index) {
        return (E) elementData[index];
    }


    private void resize() {
        int array_capacity = elementData.length;

        // 배열의 크기가 0일 경우
        if (Arrays.equals(elementData, EMPTY_ARRAY)) {
            elementData = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 배열이 가득 찼을 경우
        if (size == array_capacity) {
            int new_capacity = array_capacity * 2;

            // copy
            elementData = Arrays.copyOf(elementData, new_capacity);
            return;
        }
        // 용적의 절반 미만으로 요소가 차지하고 있을 경우
        if (size < (array_capacity / 2)) {
            int new_capacity = array_capacity / 2;

            // copy
            elementData = Arrays.copyOf(elementData, Math.max(new_capacity, DEFAULT_CAPACITY));
            return;
        }
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    private void addLast(E e) {

        // 꽉차있다면 용적 재할당
        if (size == elementData.length) {
            resize();
        }
        elementData[size] = e; // 마지막 위치에 요소 추가
        size++; // 사이즈 1 증가
    }

    @Override
    public void add(int index, E e) {

        if (index > size || index < 0) { // 영역을 벗어날 경우 예외 발생
            throw new IndexOutOfBoundsException();
        }
        if (index == size) { // index 가 마지막 위치라면 addLast 메소드로 요소추가
            addLast(e);
        } else {

            if (size == elementData.length) { // 꽉차있다면 용적 재할당
                resize();
            }

            // index 기준 후자에 있는 모든 요소를 한 칸씩 뒤로 밀기
            for (int i = size; i > index; i--) {
                elementData[i] = elementData[i - 1];
            }

            elementData[index] = e; // index 위치에 요소 할당
            size++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        E oldElement = elementData(index); // 삭제될 요소를 반환하기 위해 저장
        elementData[index] = null;

        // 삭제한 요소의 뒤에 있는 모든 요소들을 한 칸씩 당겨옴
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
            elementData[i + 1] = null;
        }
        size--;
        resize();
        return oldElement;
    }

    @Override
    public boolean remove(Object o) {

        // 삭제하고자 하는 요소의 인덱스 찾기
        int index = indexOf(o);

        // -1이라면 array 에 요소가 없다는 의미이므로 false 반환
        if (index == -1) {
            return false;
        }

        // index 위치에 있는 요소를 삭제
        remove(index);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index >= size || index < 0) { // 범위 벗어나면 예외 발생
            throw new IndexOutOfBoundsException();
        }

        return elementData(index);
    }


    @Override
    public E set(int index, E e) {
        if (index >= size || index < 0) { // 범위 벗어나면 예외 발생
            throw new IndexOutOfBoundsException();
        } else {
            // 기존 요소를 저장
            E oldElement = elementData(index);

            // 해당 위치의 요소를 교체
            elementData[index] = e;

            // 기존 요소를 반환
            return oldElement;
        }
    }

    @Override
    public boolean contains(Object o) {

        // 0 이상이면 요소가 존재한다는 뜻
        if (indexOf(o) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;

        // o 와 같은 객체(요소 값)일 경우 i(위치) 반환
        for (i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                return i;
            }
        }
        // 일치하는 것이 없을 경우 -1을 반환
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        // 모든 공간을 null 처리 해준다.
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
        resize();
    }
}
