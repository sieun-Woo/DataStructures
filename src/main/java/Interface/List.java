package Interface;

/**
 * List Interface 는 중복을 허용하면서 저장순서가 유지되는 자료구조를 구현하는데 사용힙니다.
 * List 는 ArrayList, SinglyLinkedList, DoublyLinkedList 에 의해 각각 구현됩니다.
 *
 * @author woo
 * @param <E> 리스트 요소들의 타입
 */

public interface List<E> {

    /**
     * 리스트의 끝에 요소를 추가합니다.
     *
     * @param e 리스트에 추가할 요소
     * @return {@code true}
     */

    boolean add(E e);

    /**
     * 리스트에 요소를 지정된 위치에 삽입합니다.
     * 해당 위치에 있는 요소와 후속 요소들은 오른쪽으로 이동합니다.
     *
     * @param index 요소가 삽입될 index
     * @param e 리스트에 삽입될 요소
     */
    void add(int index, E e);

    /**
     * 리스트의 index 위치에 있는 요소를 제거합니다.
     * 후속 요소들은 왼쪽으로 이동합니다.
     *
     * @param index 제거할 요소의 index
     * @return 제거된 요소를 반환
     */
    E remove(int index);

    /**
     *
     * 지정된 요소가 있는 경우 리스트에서 첫 번째 항목을 제거합니다.
     *
     * @param  o 리스트에서 제거할 요소
     * @return 리스트에 제거할 요소가 없거나 제거에 실패할
     *         경우 {@code false}를 반환하고 제거에 성공할 경우 {@code true}를 반환
     */
    boolean remove(Object o);

    /**
     * 리스트의 지정된 위치에 있는 요소를 반환합니다.
     *
     * @param index 반환할 요소의 index
     * @return 리스트의 index 위치에 있는 요소 반환
     */
    E get(int index);

    /**
     * 리스트에서 지정된 위치에 있는 요소를 지정된 요소로 교체합니다.
     *
     * @param index 교체할 요소의 index
     * @param e 지정된 위치에 저장할 요소
     * @return 지정된 위치에 있던 요소
     */
    E set(int index, E e);

    /**
     * 지정된 요소가 리스트에 있는지 여부를 반환합니다.
     *
     * @param o 리스트에서 존재 여부를 확인할 요소
     * @return 리스트에 지정된 요소가 존재할 경우 {@code true}, 존재하지 않을 경우 {@code false}를 반환
     */
    boolean contains(Object o);

    /**
     * 리스트에 지정된 요소가 처음 나타나는 인덱스를 반환합니다.
     *
     * @param o 리스트에서 위치를 찾을 요소
     * @return 리스트에서 처음으로 요소와 일치하는 위치를 반환.
     *         만약 일치하는 요소가 없을경우 -1 을 반환
     */
    int indexOf(Object o);

    /**
     * 리스트에 있는 요소의 개수를 반환합니다.
     *
     * @return 리스트에 있는 요소 개수를 반환
     */
    int size();

    /**
     * 리스트에 요소가 비어있는지를 반환합니다.
     * @return 리스트에 요소가 없을경우 {@code true}, 요소가 있을경우 {@code false}를 반환
     */
    boolean isEmpty();

    /**
     * 리스트에 있는 요소를 모두 제거합니다.
     */
    public void clear();
}
