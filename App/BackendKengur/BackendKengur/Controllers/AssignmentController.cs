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


        [HttpGet("GenerateTest/{Class}")]
        public async Task<IActionResult> GetAssignmentsByClass(string Class)
        {
            var assignments = assignmentUI.GetAssignmentsByClass(Class);
            return Ok(assignments);
        }

        [HttpGet("GetTasksFiltered/{Class}/{Level}")]
        public async Task<IActionResult> GetTasksFiltered(string Class,int Level)
        {
            var assignments = assignmentUI.GetTasksFiltered(Class,Level);
            return Ok(assignments);
        }


        [HttpPut("SendStatistic")]
        public async Task<IActionResult> SendStatistic(List<TaskEfficiencyDTO> list)
        {
            var message = assignmentUI.SendStatistic(list);
            return Ok(message);
        }

        

    }
}
