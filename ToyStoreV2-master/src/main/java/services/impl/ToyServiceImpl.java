package services.impl;

import exceptions.AddToyException;
import exceptions.InsufficientAmountException;
import exceptions.ToyNotFoundException;
import exceptions.ToyNotLargerThanException;
import mapping.dtos.ToyDto;
//mport mapping.mappers.ToyMapper;
import model.Toy;
import model.TypeToy;
import services.ToyStoreImpl;
import utilis.Constants;
import utilis.FileUtilis;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map;
/*
public class ToyServiceImpl implements ToyStoreImpl {}
   /* private List<Toy> listToy;
    public ToyServiceImpl() {
        // Inicializa la lista listToy
        listToy = new ArrayList<>();
        // Carga los juguetes desde el archivo si existe
        File file = new File(Constants.PATH_TOYS);
        if (file.exists()) {
            listToy = FileUtilis.getToys(file);
        }
    }
    @Override
    public List<ToyDto> listToys() {
        return listToy.stream().map(ToyMapper::mapFrom).toList();
    }

    @Override
    public List<ToyDto> addToy(ToyDto toyDto) throws Exception {

            if (!verifyExist(toyDto.name())) {
                listToy.add(ToyMapper.mapFrom(toyDto));
                FileUtilis.saveToys(new File(Constants.PATH_TOYS), listToy);
                return listToy.stream().map(ToyMapper::mapFrom).toList();
             }
        throw new AddToyException();
    }


    @Override
    public  Map.Entry<TypeToy,Integer> maxToy() throws Exception {
        return sort().entrySet().stream().reduce((first,second)-> second).orElse(null);
    }

    @Override
    public Map.Entry<TypeToy, Integer> minToy() throws Exception {
        return sort().entrySet().stream().findFirst().orElse(null);
    }

    @Override
    public Map<TypeToy, Integer> sort() throws Exception {
        return showByType().entrySet().stream()
                //Esta parte ordena el flujo de elementos en función de los valores
                .sorted(Map.Entry.comparingByValue())
                //recopila los elementos ordenados en un nuevo mapa.
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
    }

    @Override
    public ToyDto search(String name) throws Exception {
        if(verifyExist(name)){
            List<ToyDto> list = listToy.stream().filter(toyList-> Objects.equals(toyList.getName(), name))
                    .findFirst().stream().map(ToyMapper::mapFrom).toList();
            return list.get(0);
        }
        throw new ToyNotFoundException();
    }

    @Override
    public List<ToyDto> increase(ToyDto toyDto, int amount) throws Exception {
        Optional<Toy> optionalToy = listToy.stream()
                .filter(toy -> Objects.equals(toy.getName(), toyDto.name()))
                .findFirst();

        if (optionalToy.isPresent()) {
            Toy toy = optionalToy.get();
            int currentAmount = toy.getAmount();
            int newAmount = currentAmount + amount;
            if (newAmount < 0) {
                throw new InsufficientAmountException();
            } else {
                toy.setAmount(newAmount);
                FileUtilis.saveToys(new File(Constants.PATH_TOYS), listToy);
                return listToy.stream().map(ToyMapper::mapFrom).toList();
            }
        } else {
            throw new ToyNotFoundException();
        }
    }

    @Override
    public List<ToyDto> decrease(ToyDto toyDto, int amount) throws Exception {
        Optional<Toy> optionalToy = listToy.stream()
                .filter(toy -> Objects.equals(toy.getName(), toyDto.name()))
                .findFirst();

        if (optionalToy.isPresent()) {
            Toy toy = optionalToy.get();
            if (toy.getAmount() >= amount) {
                toy.setAmount(toy.getAmount() - amount);
                FileUtilis.saveToys(new File(Constants.PATH_TOYS), listToy);
                return listToy.stream().map(ToyMapper::mapFrom).toList();
            } else {
                throw new InsufficientAmountException();
            }
        } else {
            throw new ToyNotFoundException();
        }
    }

    @Override
    public Map<TypeToy, Integer> showByType() throws Exception {
        //TreeMap  mantiene las claves ordenadas según su orden natural

        Map<TypeToy,Integer> showByType = new TreeMap<>();
        for(Toy toy : listToy){
            TypeToy type = toy.getType();
            //put se utiliza para insertar un par clave-valor en un mapa
            showByType.put(type,showByType.getOrDefault(type,0)+1);
        }
        return showByType;
    }

    @Override
    public List<ToyDto> showLargerThan(double value) throws ToyNotLargerThanException {
        if (listToy.stream().anyMatch(toy -> toy.getPrice() > value)) {
            return listToy.stream()
                    .filter(toy -> toy.getPrice() >= value)
                    .map(ToyMapper::mapFrom)
                    .toList();
        } else {
            throw new ToyNotLargerThanException();
        }
    }

    @Override
    public Boolean verifyExist(String name) {
        return listToy.stream()
                // anyMatch() recorre  anyMatch() recorre los elementos del flujo hasta que encuentra un elemento que cumple con la condición.
                .anyMatch(e -> e.getName().equalsIgnoreCase(name));
    }

    @Override
    public Integer totalToys() throws Exception {
        Integer count = 0;
        for (Toy toy : listToy){
            count += toy.getAmount();
        }
        return count;
    }
}

    */