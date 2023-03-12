﻿using BackendKengur.DTOComponents;
using BackendKengur.Services.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace BackendKengur.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuthController : ControllerBase
    {
        private readonly IAuthService authService;

        public AuthController(IAuthService authService)
        {
            this.authService = authService;
        }

        [HttpPost("register")]
        public async Task<IActionResult> Register(RegisterDTO request)
        {
            var answer = authService.Register(request).Result;
            var message = new { message = answer.Message };
            if (answer.StatusCode.Equals(StatusCodes.Status400BadRequest))
                return BadRequest(message);

            return Ok(message);
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login(LoginDTO request)
        {
            var answer = authService.Login(request).Result;
            var message = new { message = answer.Message };

            if (answer.StatusCode.Equals(StatusCodes.Status404NotFound))
                return NotFound(message);
            if (answer.StatusCode.Equals(StatusCodes.Status403Forbidden))
                return BadRequest(message);

            return Ok(new { token = message.message });

        }

    }
}
