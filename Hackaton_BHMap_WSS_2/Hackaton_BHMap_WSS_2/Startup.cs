using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Hackaton_BHMap_WSS_2.Startup))]
namespace Hackaton_BHMap_WSS_2
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
