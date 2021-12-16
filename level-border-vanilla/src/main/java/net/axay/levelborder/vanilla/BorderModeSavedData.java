package net.axay.levelborder.vanilla;

import net.axay.levelborder.common.BorderMode;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class BorderModeSavedData extends SavedData {
    public BorderMode borderMode;

    public BorderModeSavedData() {
        this.borderMode = BorderMode.OWN;
    }

    public BorderModeSavedData(BorderMode borderMode) {
        this.borderMode = borderMode;
    }

    public static BorderModeSavedData load(CompoundTag nbt) {
        return new BorderModeSavedData(BorderMode.valueOf(nbt.getString("levelBorderMode")));
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putString("levelBorderMode", borderMode.name());
        return nbt;
    }
}
