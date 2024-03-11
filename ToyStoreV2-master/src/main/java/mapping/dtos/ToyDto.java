package mapping.dtos;

import model.TypeToy;

public record ToyDto(String name, TypeToy type, Integer price, Integer amount ){
}
