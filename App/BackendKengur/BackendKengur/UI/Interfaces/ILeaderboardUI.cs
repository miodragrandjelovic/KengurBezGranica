using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.UI.Interfaces
{
    public interface ILeaderboardUI
    {
        Task<MyResponse> AddResult(ResultDTO result);

        Task<List<Result>> GetLeaderboard(string Class);

    }
}
