package be.ipl.pae.ihm;

import be.ipl.pae.business.BusinessFactory;
import be.ipl.pae.business.BusinessFactoryImpl;
import be.ipl.pae.business.dto.organisation.OrganisationUcc;
import be.ipl.pae.business.dto.organisation.OrganisationUccImpl;
import be.ipl.pae.business.dto.user.UserUcc;
import be.ipl.pae.business.dto.user.UserUccImpl;
import be.ipl.pae.business.ucc.country.CountryUcc;
import be.ipl.pae.business.ucc.country.CountryUccImpl;
import be.ipl.pae.business.ucc.mobilities.mobility.MobilityUcc;
import be.ipl.pae.business.ucc.mobilities.mobility.MobilityUccImpl;
import be.ipl.pae.business.ucc.mobilities.mobilitycancellation.MobilityCancellationUcc;
import be.ipl.pae.business.ucc.mobilities.mobilitycancellation.MobilityCancellationUccImpl;
import be.ipl.pae.business.ucc.mobilities.mobilitychoice.MobilityChoiceUcc;
import be.ipl.pae.business.ucc.mobilities.mobilitychoice.MobilityChoiceUccImpl;
import be.ipl.pae.business.ucc.mobilities.mobilitydocument.MobilityDocumentUcc;
import be.ipl.pae.business.ucc.mobilities.mobilitydocument.MobilityDocumentUccImpl;
import be.ipl.pae.dal.DalBackendServices;
import be.ipl.pae.dal.DalServices;
import be.ipl.pae.dal.DalServicesImpl;
import be.ipl.pae.dal.country.CountryDao;
import be.ipl.pae.dal.country.CountryDaoImpl;
import be.ipl.pae.dal.mobility.MobilityDao;
import be.ipl.pae.dal.mobility.MobilityDaoImpl;
import be.ipl.pae.dal.mobilitycancellation.MobilityCancellationDao;
import be.ipl.pae.dal.mobilitycancellation.MobilityCancellationDaoImpl;
import be.ipl.pae.dal.mobilitychoice.MobilityChoiceDao;
import be.ipl.pae.dal.mobilitychoice.MobilityChoiceDaoImpl;
import be.ipl.pae.dal.mobilitydocument.MobilityDocumentDao;
import be.ipl.pae.dal.mobilitydocument.MobilityDocumentDaoImpl;
import be.ipl.pae.dal.organisation.OrganisationDao;
import be.ipl.pae.dal.organisation.OrganisationDaoImpl;
import be.ipl.pae.dal.user.UserDao;
import be.ipl.pae.dal.user.UserDaoImpl;
import be.ipl.pae.exceptions.FatalErrorException;
import be.ipl.pae.util.Config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

  private static IhmServlet servlet;

  /**
   * This method is used to start the program.
   */
  public static void main(String[] args) {
    servlet = new IhmServlet();
    BusinessFactory businessFactory = new BusinessFactoryImpl();
    DalBackendServices dalBackendServices = new DalServicesImpl();

    /*
     * Dao
     */
    UserDao userDao = new UserDaoImpl();
    userDao.setBusinessFactory(businessFactory);
    userDao.setDalBackendServices(dalBackendServices);


    OrganisationDao organisationDao = new OrganisationDaoImpl();
    organisationDao.setBusinessFactory(businessFactory);
    organisationDao.setDalBackendServices(dalBackendServices);

    CountryDao countryDao = new CountryDaoImpl();
    countryDao.setBusinessFactory(businessFactory);
    countryDao.setDalBackendServices(dalBackendServices);

    MobilityChoiceDao mobilityChoiceDao = new MobilityChoiceDaoImpl();

    mobilityChoiceDao.setBusinessFactory(businessFactory);
    mobilityChoiceDao.setDalBackendServices(dalBackendServices);

    MobilityDocumentDao mobilityDocumentDao = new MobilityDocumentDaoImpl();
    mobilityDocumentDao.setBusinessFactory(businessFactory);
    mobilityDocumentDao.setDalBackendServices(dalBackendServices);

    MobilityDao mobilityDao = new MobilityDaoImpl();

    mobilityDao.setBusinessFactory(businessFactory);
    mobilityDao.setDalBackendServices(dalBackendServices);

    MobilityCancellationDao mobilityCancellationDao = new MobilityCancellationDaoImpl();

    mobilityCancellationDao.setBusinessFactory(businessFactory);
    mobilityCancellationDao.setDalBackendServices(dalBackendServices);

    // UCC
    UserUcc userUcc = new UserUccImpl();
    userUcc.setDao(userDao);
    userUcc.setDalServices((DalServices) dalBackendServices);

    /*
     * Countries
     */

    CountryUcc countryUcc = new CountryUccImpl();
    countryUcc.setDao(countryDao);
    countryUcc.setDalServices((DalServices) dalBackendServices);

    /*
     * mobility
     */
    MobilityUcc mobilityUcc = new MobilityUccImpl();

    mobilityUcc.setDalServices((DalServices) dalBackendServices);
    mobilityUcc.setDao(mobilityDao);

    MobilityChoiceUcc mobilityChoiceUcc = new MobilityChoiceUccImpl();
    mobilityChoiceUcc.setDalServices((DalServices) dalBackendServices);
    mobilityChoiceUcc.setDao(mobilityChoiceDao);
    /*
     * Organisation
     */
    OrganisationUcc organisationUcc = new OrganisationUccImpl();
    organisationUcc.setDao(organisationDao);
    organisationUcc.setDalServices((DalServices) dalBackendServices);

    MobilityDocumentUcc mobilityDocumentUcc = new MobilityDocumentUccImpl();
    mobilityDocumentUcc.setDao(mobilityDocumentDao);
    mobilityDocumentUcc.setDalServices((DalServices) dalBackendServices);

    MobilityCancellationUcc mobilityCancellationUcc = new MobilityCancellationUccImpl();
    mobilityCancellationUcc.setDalServices((DalServices) dalBackendServices);
    mobilityCancellationUcc.setDao(mobilityCancellationDao);

    servlet.setUserUcc(userUcc);
    servlet.setCountryUcc(countryUcc);
    servlet.setMobilityUcc(mobilityUcc);
    servlet.setOrganisationUcc(organisationUcc);
    servlet.setMobilityChoiceUcc(mobilityChoiceUcc);
    servlet.setMobilityDocumentUcc(mobilityDocumentUcc);
    servlet.setMobilityCancellationUcc(mobilityCancellationUcc);

    /* Servlets */
    IhmConnexionServlet ihmConnexionServlet = new IhmConnexionServlet();
    IhmMobilityServlet ihmMobilityServlet = new IhmMobilityServlet();
    IhmUserServlet ihmUserServlet = new IhmUserServlet();
    IhmDocumentServlet ihmDocumentServlet = new IhmDocumentServlet();
    IhmOrganisationServlet ihmOrganisationServlet = new IhmOrganisationServlet();
    servlet.setIhmConnexionServlet(ihmConnexionServlet);
    servlet.setIhmMobilityServlet(ihmMobilityServlet);
    servlet.setIhmUserServlet(ihmUserServlet);
    servlet.setIhmDocumentServlet(ihmDocumentServlet);
    servlet.setIhmOrganisationServlet(ihmOrganisationServlet);

    startServer();
  }

  /**
   * This method is used to start the server.
   */
  private static void startServer() {

    WebAppContext context = new WebAppContext();
    context.setContextPath(Config.getProperty("context_path", ""));
    context.addServlet(new ServletHolder(servlet), "/");
    context.setResourceBase(Config.getProperty("resource_base", ""));

    /*
     * Set the behaviour of the servlet { PROD - DEV } DEV : Load ressources PROD : Cache ressources
     */
    if (Config.getProperty("mode", "").equals("production")) {
      servlet.setMode(ConfigurationMode.PROD);
    }

    Server server = new Server(Integer.valueOf(Config.getProperty("port", "")));
    server.setHandler(context);

    try {
      server.start();
    } catch (Exception ignored) {
      throw new FatalErrorException();
    }
  }

}
