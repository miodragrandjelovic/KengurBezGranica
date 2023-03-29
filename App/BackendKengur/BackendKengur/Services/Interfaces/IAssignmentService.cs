using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.Services.Interfaces
{
    public interface IAssignmentService
    {
        Assignment AddNewAssignment(AssignmentDTO assignmentDTO);
    }
}
