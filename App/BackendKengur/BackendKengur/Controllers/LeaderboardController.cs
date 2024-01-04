using BackendKengur.DTOComponents;
using BackendKengur.Models;
using BackendKengur.UI.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace BackendKengur.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class LeaderboardController : ControllerBase
    {
        private readonly ILeaderboardUI leaderboardUI;

        public LeaderboardController(ILeaderboardUI leaderboardUI)
        {
            this.leaderboardUI = leaderboardUI;
        }

        [HttpPost("AddResult")]
        public async Task<IActionResult> AddResult(ResultDTO result)
        {
            var response = await leaderboardUI.AddResult(result);
            return Ok(response);
        }


        [HttpGet("Leaderboard/{Class}")]
        public async Task<ActionResult<List<Result>>> GetLeaderboard(string Class)
        {
            var leaderboard = await leaderboardUI.GetLeaderboard(Class);
            return Ok(leaderboard);
        }


    }
}
