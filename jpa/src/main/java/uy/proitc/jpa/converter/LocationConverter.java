package uy.proitc.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import uy.proitc.jpa.entity.Location;

@Converter(autoApply = true)
public class LocationConverter implements AttributeConverter<Location, String> {

  @Override
  public String convertToDatabaseColumn(Location attribute) {
    return attribute.toString();
  }

  @Override
  public Location convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return new Location(0, 0);
    }
    String[] coordinates = dbData.split(",");
    return new Location(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
  }
}
