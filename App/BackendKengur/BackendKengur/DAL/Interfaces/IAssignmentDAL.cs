using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.DAL.Interfaces
{
    public interface IAssignmentDAL
    {
        Task<Assignment> AddNewAssignment(Assignment assignment);

        Task<List<Assignment>> GetAssignmentsByClass(string Class);

        Task<List<Assignment>> GetTasksFiltered(string Class,int Level);

        Task<bool> SendStatistic(List<TaskEfficiencyDTO> list);

    }
}
