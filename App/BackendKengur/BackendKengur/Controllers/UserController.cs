using BackendKengur.UI.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace BackendKengur.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IUserUI userUI;

        public UserController(IUserUI userUI)
        {
            this.userUI = userUI;
        }

        [HttpGet("GetUser/{email}")]
        public async Task<IActionResult> GetUser(string email)
        {
            var user = userUI.GetUser(email);
            if (user == null)
                return NotFound();
            return Ok(user);

        }

    }
}
