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

        public Assignment AddNewAssignment(AssignmentDTO assignmentDTO)
        {
            return assignmentService.AddNewAssignment(assignmentDTO);
        }
    }
}
