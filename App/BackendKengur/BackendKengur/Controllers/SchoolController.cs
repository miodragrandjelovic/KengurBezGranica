using BackendKengur.Models;
using Microsoft.AspNetCore.Mvc;
using BackendKengur.UI.Interfaces;

namespace BackendKengur.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SchoolController : ControllerBase
    {

        private readonly ISchoolUI schoolUI;

        public SchoolController(ISchoolUI school)
        {
            schoolUI = school;
        }

        [HttpGet("GetAllSchools")]
        public async Task<ActionResult<List<School>>> GetAllSchools()
        {
            var allSchools = await schoolUI.GetAllSchools();
            return Ok(allSchools);
        }

        [HttpGet("GetSchoolById/{id}")]
        public async Task<ActionResult<School>> GetSchoolById(string id)
        {
            var school = await schoolUI.GetSchoolById(id);
            return Ok(school);
        }

        [HttpPost("AddNewSchool")]
        public async Task<ActionResult<School>> AddNewSchool(School school)
        {
            var createdSchool = await schoolUI.AddNewSchool(school);
            return Ok(createdSchool);
        }

        [HttpDelete("DeleteSchool/{id}")]
        public IActionResult DeleteSchool(string id)
        {
            schoolUI.DeleteSchool(id);
            return Ok();
        }

        [HttpGet("SearchSchools/{name}")]
        public async Task<ActionResult<List<School>>> SearchSchools(string name)
        {
            var schools = await schoolUI.SearchSchools(name);
            return Ok(schools);
        }


    }
}
