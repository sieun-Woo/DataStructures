package _02_SinglyLinkedList;

import Interface.List;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

    private Node<E> head; // 리스트의 첫 노드
    private Node<E> tail; // 리스트의 마지막 노드
    private int size; // 요소 개수

    // 생성자
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 특정 위치의 노드를 반환하는 메소드
    private Node<E> search(int index) {

        // 범위 밖(잘못된 위치)일 경우 예외 던지기
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = head; // head 가 가리키는 노드부터 시작

        for (int i = 0; i < index; i++) {
            x = x.next; // x 노드의 다음 노드를 x 에 저장한다.
        }
        return x;
    }

    public void addFirst(E value) {

        Node<E> newNode = new Node<E>(value); // 새 노드 생성
        newNode.next = head; // 새 노드의 다음 노드로 head 노드를 연결
        head = newNode; // head 가 가리키는 노드를 새 노드로 변경
        size++;

        /**
         *  다음에 가맄리 노드가 없는 경우(=데이터가 새 노드 밖에 없는 경우)
         *  데이터가 한 개(새 노드) 밖에 없으므로 새 노드는 처음 시작노드 이자
         *  마지막 노드다. 즉 tail = head 다.
         */

        if (head.next == null) {
            tail = head;
        }
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public void add(int index, E e) {

        // 잘못된 인덱스를 참조할 경우 예외 발생
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // 추가하려는 index 가 가장 앞에 추가하려는 경우 addFirst 호출
        if (index == 0) {
            addFirst(e);
            return;
        }
        // 추가하려는 index 가 마지막 위치일 경우 addLast 호출
        if (index == size) {
            addLast(e);
            return;
        }

        // 추가하려는 위치의 이전 노드
        Node<E> prev_Node = search(index - 1);

        // 추가하려는 위치의 노드
        Node<E> next_Node = prev_Node.next;

        // 추가하려는 노드
        Node<E> newNode = new Node<>(e);

        /**
         * 이전 노드가 가리키는 노드를 끊은 뒤
         * 새 노드로 변경해준다.
         * 또한 새 노드가 가리키는 노드는 next_Node 로
         * 설정해준다.
         */
        prev_Node.next = null;
        prev_Node.next = newNode;
        newNode.next = next_Node;
        size++;

    }

    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // 새 노드 생성

        if (size == 0) { // 처음 넣는 노드일 경우 addFirst 로 추가
            addFirst(e);
            return;
        }

        /**
         * 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
         * tail 이 가리키는 노드를 새 노드로 바꿔준다.
         */
        tail.next = newNode;
        tail = newNode;
        size++;
    }


    /**
     * 가장 앞에 있는 요소를 제거하는 메소드
     * head 가 가리키는 요소를 제거한다.
     * @return 삭제된 요소
     */

    public E remove() {

        Node<E> headNode = head;

        if (headNode == null) {
            throw new NoSuchElementException();
        }

        // 삭제된 노드를 반환하기 위한 임시 변수
        E element = head.data;

        // head 의 다음 노드
        Node<E> nextNode = head.next;

        // head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;

        // head 가 다음 노드를 가리키도록 업데이트
        head = nextNode;
        size--;

        /**
         * 삭제된 요소가 리스트의 유일한 요소였을 경우
         * 그 요소는 head 이자 tail 이었으므로
         * 삭제되면서 tail 도 가리킬 요소가 없기 때문에
         * size 가 0일 경우 tail 도 null 로 변경
         */
        if (size == 0) {
            tail = null;
        }
        return element;
    }

    @Override
    public E remove(int index) {

        // 삭제하려는 노드가 첫 번째 원소일 경우
        if (index == 0) {
            return remove();
        }

        // 잘못된 범위에 대한 예외
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> prevNode = search(index - 1); // 삭제할 노드의 이전 노드
        Node<E> removedNode = prevNode.next; // 삭제할 노드
        Node<E> nextNode = removedNode.next; // 삭제할 노드의 다음 노드

        E element = removedNode.data; // 삭제되는 노드의 데이터를 반환하기 위한 임시변수

        // 이전 노드가 가리키는 노드를 삭제하려는 노드의 다음노드로 변경
        prevNode.next = nextNode;

        // 만약 삭제했던 노드가 마지막 노드라면 tail 을 prevNode 로 갱신
        if (prevNode.next == null) {
            tail = prevNode;
        }
        // 데이터 삭제
        removedNode.next = null;
        removedNode.data = null;
        size--;

        return element;
    }

    @Override
    public boolean remove(Object o) {

        Node<E> prevNode = head;
        Node<E> x = head; // removedNode

        // value 와 일치하는 노드를 찾는다.
        for (; x != null; x = x.next) {
            if (o.equals(x.data)) {
                break;
            }
            prevNode = x;
        }

        // 일치하는 요소가 없을 경우 false 반환
        if (x == null) {
            return false;
        }

        // 만약 삭제하려는 노드가 head 라면 기존 remove()를 사용
        if (x.equals(head)) {
            remove();
            return true;
        }

        else {
            // 이전 노드의 링크를 삭제하려는 노드의 다음 노드로 연결
            prevNode.next = x.next;

            // 만약 삭제했던 노드가 마지막 노드라면 tail 을 prevNode 로 갱신
            if (prevNode.next == null) {
                tail = prevNode;
            }
            x.data = null;
            x.next = null;
            size--;
            return true;
        }
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public E set(int index, E e) {

        Node<E> replaceNode = search(index);
        E oldValue = replaceNode.data;
        replaceNode.data = null;
        replaceNode.data = e;

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            if (o.equals(x.data)) {
                return index;
            }
            index++;
        }
        // 찾고자 하는 요소를 찾지 못했을 경우 -1 반환
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
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
        for (Node<E> x = head; x != null;) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x = nextNode;
        }
        head = tail = null;
        size = 0;
    }

    private static class Node<E> {

        E data;
        Node<E> next; // 다음 노드객체를 가리키는 래퍼런스 변수

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
}
