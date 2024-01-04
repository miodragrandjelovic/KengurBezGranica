using BackendKengur.DTOComponents;
using BackendKengur.Models;
using BackendKengur.Services.Interfaces;
using BackendKengur.UI.Interfaces;

namespace BackendKengur.UI
{
    public class AssignmentUI : IAssignmentUI
    {
        private readonly IAssignmentService assignmentService;

        public AssignmentUI(IAssignmentService assignmentService)
        {
            this.assignmentService = assignmentService;
        }

        public async Task<Assignment> AddNewAssignment(AssignmentDTO assignmentDTO)
        {
            return await assignmentService.AddNewAssignment(assignmentDTO);
        }

        public async Task<List<Assignment>> GetAssignmentsByClass(string Class)
        {
            return await assignmentService.GetAssignmentsByClass(Class);
        }

        public async Task<List<Assignment>> GetTasksFiltered(string Class, int Level)
        {
           return await assignmentService.GetTasksFiltered(Class, Level);
        }

        public async Task<bool> SendStatistic(List<TaskEfficiencyDTO> list)
        {
            return await assignmentService.SendStatistic(list);
        }
    }
}
