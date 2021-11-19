package com.example.petstore.db;
import java.util.ArrayList;
import java.util.List;
import com.example.petstore.Pet;

public class DB {
    private static List<Pet> petTable=new ArrayList<Pet>();

    //private static List<PetType> petTypeTable=new ArrayList<PetType>();

    public static List<Pet> getPetTable(){
        return petTable;
    }

    public static Pet savePet(Pet pet){
        petTable.add(pet);
        return pet;
    }
    public static Pet updatePet(Pet pet, Pet newpet){
        pet.setPetName(newpet.getPetName());
        pet.setPetAge(newpet.getPetAge());
        pet.setPetType(newpet.getPetType());
        return pet;
    }
    public static Pet deletePet(Pet pet){
        petTable.remove(pet);
        return pet;
    }
}
