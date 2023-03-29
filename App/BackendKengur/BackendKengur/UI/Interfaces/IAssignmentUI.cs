using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.UI.Interfaces
{
    public interface IAssignmentUI
    {
        Assignment AddNewAssignment(AssignmentDTO assignmentDTO);

        List<Assignment> GetAssignmentsByClass(string Class);
    }
}
