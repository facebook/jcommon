package com.facebook.memory.data.structures;

import com.google.common.base.MoreObjects;

import com.facebook.memory.data.types.definitions.PointerSlot;

/**
 * this is an overlay/view of a LinkedListBucketNode to match OffHeapCacheEntry.
 */
public class LinkedListNodeCacheEntry implements OffHeapCacheEntry {
  private static final PointerSlot NEXT_ADDR = LinkedListBucketNodeWithLruCache.CACHE_NEXT_ADDR;
  private static final PointerSlot PREVIOUS_ADDR = LinkedListBucketNodeWithLruCache.CACHE_PREVIOUS_ADDR;

  private final long linkedListNodeAddress;

  public LinkedListNodeCacheEntry(long linkedListNodeAddress) {
    this.linkedListNodeAddress = linkedListNodeAddress;
  }

  public LinkedListNodeCacheEntry(OffHeap offHeap) {
    this(offHeap.getAddress());
  }

  @Override
  public long getPrevious() {
    return PREVIOUS_ADDR.accessor(linkedListNodeAddress).get();
  }

  @Override
  public void setPrevious(long value) {
    PREVIOUS_ADDR.accessor(linkedListNodeAddress).put(value);
  }

  @Override
  public long getNext() {
    return NEXT_ADDR.accessor(linkedListNodeAddress).get();
  }

  @Override
  public void setNext(long value) {
    NEXT_ADDR.accessor(linkedListNodeAddress).put(value);
  }

  @Override
  public long getDataPointer() {
    return linkedListNodeAddress;
  }

  @Override
  public void setDataPointer(long value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getAddress() {
    return linkedListNodeAddress;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("next", getNext())
      .add("previous", getPrevious())
      .add("data", getDataPointer())
      .toString();
  }
}
