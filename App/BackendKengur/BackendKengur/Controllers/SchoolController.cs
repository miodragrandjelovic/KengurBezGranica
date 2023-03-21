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
        public async Task<IActionResult> GetAllSchools()
        {
            var allSchools = schoolUI.GetAllSchools();
            return Ok(allSchools);
        }

        [HttpGet("GetSchoolById/{id}")]
        public async Task<IActionResult> GetSchoolById(string id)
        {
            var school = schoolUI.GetSchoolById(id);
            return Ok(school);
        }

        [HttpPost("AddNewSchool")]
        public async Task<IActionResult> AddNewSchool(School school)
        {
            var createdSchool = schoolUI.AddNewSchool(school);
            return Ok(createdSchool);
        }

        [HttpDelete("DeleteSchool/{id}")]
        public async Task<IActionResult> DeleteSchool(string id)
        {
            schoolUI.DeleteSchool(id);
            return Ok();
        }

        [HttpGet("SearchSchools/{name}")]
        public async Task<IActionResult> SearchSchools(string name)
        {
            var schools = schoolUI.SearchSchools(name);
            return Ok(schools);
        }


    }
}
