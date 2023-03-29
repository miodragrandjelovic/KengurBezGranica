using BackendKengur.DTOComponents;
using BackendKengur.Models;
using BackendKengur.UI.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace BackendKengur.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AssignmentController : ControllerBase
    {
        private readonly IAssignmentUI assignmentUI;

        public AssignmentController(IAssignmentUI assignmentUI)
        {
            this.assignmentUI = assignmentUI;
        }

        [HttpPost("AddNewAssignment")]
        public async Task<IActionResult> AddNewAssignment([FromForm] AssignmentDTO assignmentDTO)
        {
            var createdAssignmentl = assignmentUI.AddNewAssignment(assignmentDTO);
            return Ok(createdAssignmentl);
        }

    }
}
