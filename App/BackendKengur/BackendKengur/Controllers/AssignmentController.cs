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
        public async Task<ActionResult<Assignment>> AddNewAssignment([FromForm] AssignmentDTO assignmentDTO)
        {
            var createdAssignmentl = await assignmentUI.AddNewAssignment(assignmentDTO);
            return Ok(createdAssignmentl);
        }


        [HttpGet("GenerateTest/{Class}")]
        public async Task<ActionResult<List<Assignment>>> GetAssignmentsByClass(string Class)
        {
            var assignments = await assignmentUI.GetAssignmentsByClass(Class);
            return Ok(assignments);
        }

        [HttpGet("GetTasksFiltered/{Class}/{Level}")]
        public async Task<ActionResult<List<Assignment>>> GetTasksFiltered(string Class,int Level)
        {
            var assignments = await assignmentUI.GetTasksFiltered(Class,Level);
            return Ok(assignments);
        }


        [HttpPut("SendStatistic")]
        public async Task<ActionResult> SendStatistic(List<TaskEfficiencyDTO> list)
        {
            var result = await assignmentUI.SendStatistic(list);
            if (result)
                return Ok();
            return BadRequest("Something went wrong.");
        }

        

    }
}
