using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.UI.Interfaces
{
    public interface ILeaderboardUI
    {
        MyResponse AddResult(ResultDTO result);

        List<Result> GetLeaderboard(string Class);

    }
}
