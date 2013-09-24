import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

import com.iokays.utils.domain.Level;

public class Main {
	public static void main(String[] args) {
		System.out.println(Level.values());
	}

	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private ManagementService managementService;
	@Resource
	private IdentityService identityService;
}
