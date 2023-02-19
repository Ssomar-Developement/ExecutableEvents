package com.ssomar.executableevents.events.block;

import com.ssomar.score.SCore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class BlockEvt implements Listener {


    //	@EventHandler
    //	public void onBlockBurnEvent(BlockBurnEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onBlockCanBuildEvent(BlockCanBuildEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onBlockCookEvent(BlockCookEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onBlockDispenseArmorEvent(BlockDispenseArmorEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onBlockDispenseEvent(BlockDispenseEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onBlockDropItemEvent(BlockDropItemEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    //Dont decomment this event, it active BlockBreakEvent
	/*@EventHandler
	public void onBlockExpEvent(BlockExpEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}
	 */
    //	@EventHandler
    //	public void onBlockExplodeEvent(BlockExplodeEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}
    //
    //	@EventHandler
    //	public void onBlockFadeEvent(BlockFadeEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

    /* To cancel the usage of bone meal on seagrass when seagrass cant grow up*/
    @EventHandler
    public void onPlayerBoneMealOnSeagrassEvent(PlayerInteractEvent e) {
        if (!SCore.is1v12Less()) {

            PlayerInventory inv = e.getPlayer().getInventory();

            ItemStack mainHand = inv.getItemInMainHand();
            if (!mainHand.getType().equals(Material.BONE_MEAL)) {
                ItemStack offHand = inv.getItemInOffHand();
                if (!offHand.getType().equals(Material.BONE_MEAL)) {
                    return;
                }
            }

            if (Action.RIGHT_CLICK_BLOCK.equals(e.getAction()) && e.getClickedBlock() != null) {
                Block b = e.getClickedBlock();
                if (b.getType().equals(Material.SEAGRASS)) {
                    Location locB = b.getLocation();
                    locB.add(0, 1, 0);
                    Block verifyB = locB.getBlock();
                    if (verifyB.getType() != Material.WATER) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

	/*@EventHandler
	public void onBlockFormEvent(BlockFormEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onBlockFromToEvent(BlockFromToEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onBlockGrowEvent(BlockGrowEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onBlockIgniteEvent(BlockIgniteEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onBlockMultiPlaceEvent(BlockMultiPlaceEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}*/

    //	@EventHandler
    //	public void onBlockPhysicsEvent(BlockPhysicsEvent e) {
    //
    //		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());
    //
    //		EventsManager.getInstance().manage(e);
    //	}

	/*@EventHandler
	public void onBlockPistonExtendEvent(BlockPistonExtendEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onBlockPistonRetractEvent(BlockPistonRetractEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}*/

	/*@EventHandler
	public void onBlockRedstoneEvent(BlockRedstoneEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onBlockShearEntityEvent(BlockShearEntityEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onBlockSpreadEvent(BlockSpreadEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onCauldronLevelChangeEvent(CauldronLevelChangeEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onEntityBlockFormEvent(EntityBlockFormEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onFluidLevelChangeEvent(FluidLevelChangeEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onLeavesDecayEvent(LeavesDecayEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onMoistureChangeEvent(MoistureChangeEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onNotePlayEvent(NotePlayEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onSignChangeEvent(SignChangeEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}

	@EventHandler
	public void onSpongeAbsorbEvent(SpongeAbsorbEvent e) {

		SsomarDev.testMsg("étape 1 "+this.toString()+ " "+e.getEventName());

		EventsManager.getInstance().manage(e);
	}*/

}
