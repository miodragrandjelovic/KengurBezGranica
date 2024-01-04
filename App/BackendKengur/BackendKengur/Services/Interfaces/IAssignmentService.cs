using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.Services.Interfaces
{
    public interface IAssignmentService
    {
        Task<Assignment> AddNewAssignment(AssignmentDTO assignmentDTO);
        Task<List<Assignment>> GetAssignmentsByClass(string Class);
        Task<List<Assignment>> GetTasksFiltered(string Class,int Level);
        Task<bool> SendStatistic(List<TaskEfficiencyDTO> list);

    }
}
