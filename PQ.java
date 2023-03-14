//-----------------------------------------------------
// Title: Priority Queue class

// Description: This class is used for creating priority queue.
//-----------------------------------------------------

// Max heap based on coordinate x values
class PQ {
    private Coordinate [] heap;
    private int size;

    // Constructor of Heap based priority queue
    public PQ(int capacity) {
        heap = new Coordinate[capacity];
        size = 0;
    }

    // returns the index of the parent node
    public static int parent(int i) {
        return (i - 1) / 2;
    }

    // return the index of the left child 
    public static int leftChild(int i) {
        return 2*i + 1;
    }

    // return the index of the right child 
    public static int rightChild(int i) { 
        return 2*i + 2;
    }

    // insert the item at the appropriate position
    public void enqueue(Coordinate data) {

        // first insert the time at the last position of the array 
        // and move it up
        heap[size] = data;
        size = size + 1;


        // move up until the heap property satisfies
        int i = size - 1;
        while (i != 0 && heap[parent(i)].x < heap[i].x) {
            Coordinate temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = parent(i);
        }
    }

    // moves the item at position i of array a
    // into its appropriate position
    public void maxHeapify(int i){
        // find left child node
        int left = leftChild(i);

        // find right child node
        int right = rightChild(i);

        // find the largest among 3 nodes
        int largest = i;

        // check if the left node is larger than the current node
        if (left <= size && heap[left].x > heap[largest].x) {
            largest = left;
        }

        // check if the right node is larger than the current node 
        // and left node
        if (right <= size && heap[right].x > heap[largest].x) {
            largest = right;
        }

        // swap the largest node with the current node 
        // and repeat this process until the current node is larger than 
        // the right and the left node
        if (largest != i) {
        	Coordinate temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            maxHeapify(largest);
        }

    }

    // returns the  maximum item of the heap
    public Coordinate peek() {
        return heap[0];
    }

    // deletes the max item and return
    public Coordinate dequeue() {
    	Coordinate maxItem = heap[0];

        // replace the first item with the last item
        heap[0] = heap[size - 1];
        size = size - 1;

        // maintain the heap property by heapifying the first item
        maxHeapify(0);
        return maxItem;
    }

	public int getSize() {
		return size;
	}

}