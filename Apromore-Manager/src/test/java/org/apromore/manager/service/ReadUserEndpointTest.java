package org.apromore.manager.service;

import org.apromore.dao.model.User;
import org.apromore.exception.UserNotFoundException;
import org.apromore.manager.canoniser.ManagerCanoniserClient;
import org.apromore.model.ObjectFactory;
import org.apromore.model.ReadUserInputMsgType;
import org.apromore.model.ReadUserOutputMsgType;
import org.apromore.service.CanonicalConverter;
import org.apromore.service.CanoniserService;
import org.apromore.service.ClusterService;
import org.apromore.service.DeploymentService;
import org.apromore.service.DomainService;
import org.apromore.service.FormatService;
import org.apromore.service.FragmentService;
import org.apromore.service.MergeService;
import org.apromore.service.PluginService;
import org.apromore.service.ProcessService;
import org.apromore.service.SessionService;
import org.apromore.service.SimilarityService;
import org.apromore.service.UserService;
import org.apromore.service.helper.UIHelper;
import org.apromore.service.helper.UserInterfaceHelper;
import org.apromore.service.impl.CanonicalConverterAdapter;
import org.apromore.service.impl.CanoniserServiceImpl;
import org.apromore.service.impl.ClusterServiceImpl;
import org.apromore.service.impl.DeploymentServiceImpl;
import org.apromore.service.impl.DomainServiceImpl;
import org.apromore.service.impl.FormatServiceImpl;
import org.apromore.service.impl.FragmentServiceImpl;
import org.apromore.service.impl.MergeServiceImpl;
import org.apromore.service.impl.PluginServiceImpl;
import org.apromore.service.impl.ProcessServiceImpl;
import org.apromore.service.impl.SessionServiceImpl;
import org.apromore.service.impl.SimilarityServiceImpl;
import org.apromore.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBElement;

import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

/**
 * Test the Read User method on the Manager Portal Endpoint WebService.
 */
public class ReadUserEndpointTest {

    private ManagerPortalEndpoint endpoint;

    private DeploymentService deploymentService;
    private CanoniserService canoniserService;
    private PluginService pluginService;
    private FragmentService fragmentSrv;
    private ProcessService procSrv;
    private ClusterService clusterService;
    private FormatService frmSrv;
    private DomainService domSrv;
    private UserService userSrv;
    private SimilarityService simSrv;
    private MergeService merSrv;
    private SessionService sesSrv;
    private UserInterfaceHelper uiHelper;
    private CanonicalConverter convertor;
    private ManagerCanoniserClient caClient;

    @Before
    public void setUp() throws Exception {
        deploymentService = createMock(DeploymentServiceImpl.class);
        pluginService = createMock(PluginServiceImpl.class);
        fragmentSrv = createMock(FragmentServiceImpl.class);
        canoniserService = createMock(CanoniserServiceImpl.class);
        procSrv = createMock(ProcessServiceImpl.class);
        clusterService = createMock(ClusterServiceImpl.class);
        frmSrv = createMock(FormatServiceImpl.class);
        domSrv = createMock(DomainServiceImpl.class);
        userSrv = createMock(UserServiceImpl.class);
        simSrv = createMock(SimilarityServiceImpl.class);
        merSrv = createMock(MergeServiceImpl.class);
        sesSrv = createMock(SessionServiceImpl.class);
        uiHelper = createMock(UIHelper.class);
        convertor = createMock(CanonicalConverterAdapter.class);
        caClient = createMock(ManagerCanoniserClient.class);

        endpoint = new ManagerPortalEndpoint(deploymentService, pluginService, fragmentSrv, canoniserService, procSrv,
                clusterService, frmSrv, domSrv, userSrv, simSrv, merSrv, sesSrv, uiHelper, convertor, caClient);
    }




    @Test
    public void testInvokeReadUser() throws Exception {
        ReadUserInputMsgType msg = new ReadUserInputMsgType();
        msg.setUsername("someone");
        JAXBElement<ReadUserInputMsgType> request = new ObjectFactory().createReadUserRequest(msg);

        User user = new User();
        expect(userSrv.findUserByLogin(msg.getUsername())).andReturn(user);

        replay(userSrv);

        JAXBElement<ReadUserOutputMsgType> response = endpoint.readUser(request);
        Assert.assertNotNull(response.getValue().getResult());
        Assert.assertNotNull(response.getValue().getUser());
        Assert.assertEquals("Result Code Doesn't Match", response.getValue().getResult().getCode().intValue(), 0);
        Assert.assertEquals("UserType shouldn't contain anything", response.getValue().getUser().getFirstname(), null);

        verify(userSrv);
    }

    @Test
    public void testInvokeReadUserThrowsException() throws Exception {
        ReadUserInputMsgType msg = new ReadUserInputMsgType();
        msg.setUsername("someone");
        JAXBElement<ReadUserInputMsgType> request = new ObjectFactory().createReadUserRequest(msg);

        expect(userSrv.findUserByLogin(msg.getUsername())).andThrow(new UserNotFoundException());

        replay(userSrv);

        JAXBElement<ReadUserOutputMsgType> response = endpoint.readUser(request);
        Assert.assertNotNull(response.getValue().getResult());
        Assert.assertEquals("Result Code Doesn't Match", response.getValue().getResult().getCode().intValue(), -1);

        verify(userSrv);
    }
}

