using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.Services.Interfaces
{
    public interface IAssignmentService
    {
        Assignment AddNewAssignment(AssignmentDTO assignmentDTO);

        List<Assignment> GetAssignmentsByClass(string Class);

        List<Assignment> GetTasksFiltered(string Class,int Level);

        string SendStatistic(List<TaskEfficiencyDTO> list);

    }
}
