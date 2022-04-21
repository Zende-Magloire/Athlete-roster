import java.util.Arrays;

public class MyList <T>
{
    private class Node<T>
        {
            T       data;
            Node<T> next;
            Node<T> prev;

            public Node(T stuff)
            {
                data = stuff;
                next = null;
                prev = null;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "data=" + data +
                        '}';
            }
        }

        private Node<T> mHead;
        private Node<T> mTail;
        private int     mSize;

        public MyList()
        {
            mHead = null;
            mTail = null;
            mSize = 0;
        }

        public boolean isEmpty()
        {
            return (mHead == null);
        }

        public int getSize()
        {
            return mSize;
        }

        public T get(int idx)
        {
            Node<T> tmp = find(idx);
            return tmp.data;
        }

        public void set(int idx, T stuff)
        {
            Node<T> tmp = find(idx);
            tmp.data = stuff;
        }

        public void append(T stuff)
        {
            Node<T> node = new Node<>(stuff);

            if (isEmpty())
            {
                mHead = mTail = node;
            }
            else
            {
                mTail.next = node;
                node.prev = mTail;
                mTail = node;
            }

            mSize++;
        }

        public void insert(int idx, T stuff)
        {
            if (isEmpty() || idx < 0)
                throw new IndexOutOfBoundsException();

            Node<T> node = new Node<>(stuff);

            if (idx == 0)
            {
                node.next = mHead;
                mHead = node;
            }
            else
            {
                Node<T> tmp = find(idx-1);
                node.next = tmp.next;
                tmp.next = node;
            }

            mSize++;
        }

        private Node<T> find(int idx)
        {
            if (isEmpty() || idx < 0 || idx > mSize-1)
                throw new IndexOutOfBoundsException();

            Node<T> tmp = mHead;
            for (int i=0; i<idx; i++)
                tmp = tmp.next;
            return tmp;
        }
    }

