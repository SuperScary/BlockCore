package net.superscary.blockcore.api.power;

import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class PowerHandler implements IEnergyStorage {

    private final IEnergyStorage energyStorage;

    public PowerHandler (IEnergyStorage energyStorage) {
        this.energyStorage = energyStorage;
    }

    @Override
    public int receiveEnergy (int maxReceive, boolean simulate) {
        return energyStorage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy (int maxExtract, boolean simulate) {
        return energyStorage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored () {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored () {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract () {
        return energyStorage.canExtract();
    }

    @Override
    public boolean canReceive () {
        return energyStorage.canReceive();
    }

    public static EnergyStorage createEnergyStorage (int capacity, int maxReceive, int maxExtract) {
        return new EnergyStorage(capacity, maxReceive, maxExtract);
    }

    public static EnergyStorage createEnergyStorage (int capacity, int maxReceive) {
        return createEnergyStorage(capacity, maxReceive, maxReceive);
    }

}
