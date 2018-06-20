public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
        this.size = 0;
    }

    public SinglyLinkedList(ListItem<T> head) {
        this.head = head;
        this.size = getSize();
    }

    public int getSize() {
        size = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            size++;
        }
        return size;
    }

    public ListItem<T> getFirst() {
        return head;
    }

    public T getData(int index) {
        ListItem<T> p = head;
        for (int i = 0; i <= index; i++) {
            p = p.getNext();
        }
        return p.getData();
    }

    public T setData(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index задан не корректно");
        }

        ListItem<T> p = head;
        for (int i = 0; i <= index; i++) {
            p.setNext(p.getNext());
        }
        T tmp = p.getData();
        p.setData(data);
        return tmp;
    }

    public T remove(int index) {
        ListItem<T> p = head;
        for (int i = 0; i < index; i++) {
            p.setNext(p.getNext());
        }
        T result = p.getNext().getData();
        p.setNext(p.getNext().getNext());
        size--;
        return result;
    }

    public boolean remove(T data) {
        int i = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getData().equals(data)) {
                remove(i);
                size--;
                return true;
            }
            i++;
        }
        return false;
    }

    public T removeFirst() {
        T result = head.getData();
        head = head.getNext();
        size--;
        return result;
    }

    public void addFirst(T data) {
        ListItem<T> p = new ListItem<>(data);
        if (head != null) {
            p.setNext(head);
            head = p;
        } else{
            head = p;
            head.setNext(null);
        }
        size++;
    }

    public void addLast(T data) {
        ListItem<T> node = new ListItem<>(data);
        ListItem<T> p=head;
        for(int i=0; i<size-1;i++){
            p=p.getNext();
        }
        if (p==null){
            head = node;
            node.setNext(null);
        }else {
            p.setNext(node);
            node.setNext(null);
        }
        size++;
    }

    public void add(int index, T data) {
        ListItem<T> p = head;
        for (int i = 0; i < index-1; i++) {
            p = p.getNext();
        }
        ListItem<T> node = new ListItem<>(data);
        node.setNext(p.getNext());
        p.setNext(node);
        size++;
    }

    public SinglyLinkedList<T> reverseList() {
        ListItem<T> reversedPart = null;
        ListItem<T> current = head;
        while (current != null) {
            ListItem<T> next = current.getNext();
            current.setNext(reversedPart);
            reversedPart = current;
            current = next;
        }
        head = reversedPart;
        return this;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();
        ListItem<T> p = head;
        for (int i = 0; i < size; i++) {
            result.addLast(p.getData());
            p = p.getNext();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        ListItem<T> p = head;
        for (int i = 0; i < size; i++) {
            sb.append(p.getData().toString());
            if (i < size - 1) {
                sb.append(", ");
            }
            p = p.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
