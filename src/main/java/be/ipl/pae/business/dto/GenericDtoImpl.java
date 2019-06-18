package be.ipl.pae.business.dto;


public abstract class GenericDtoImpl implements GenericDto {

  protected Integer id;

  @Override
  public Integer getId() {
    return id;
  }
}
