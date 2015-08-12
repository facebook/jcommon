package com.facebook.memory.data.types.definitions;

import sun.misc.Unsafe;

import com.facebook.memory.UnsafeAccessor;

public class PointerAccessor extends AbstractSlotAccessor {
  private final Unsafe unsafe = UnsafeAccessor.get();

  public PointerAccessor(long baseAddress, SlotOffsetMapper slotOffsetMapper) {
    super(baseAddress, SlotType.ADDRESS.getStaticSlotsSize(), slotOffsetMapper);
  }

  public PointerAccessor(
    SlotAccessor previousSlotAccessor,
    SlotOffsetMapper slotOffsetMapper
  ) {
    super(previousSlotAccessor, SlotType.ADDRESS.getStaticSlotsSize(), slotOffsetMapper);
  }

  public void put(long pointer) {
    unsafe.putAddress(getSlotAddress(), pointer);
  }

  public long get() {
    return unsafe.getAddress(getSlotAddress());
  }
}
