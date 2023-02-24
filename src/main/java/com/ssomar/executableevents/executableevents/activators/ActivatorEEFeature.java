package com.ssomar.executableevents.executableevents.activators;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.commands.DebugMode;
import com.ssomar.executableevents.configs.GeneralConfig;
import com.ssomar.executableevents.events.optimize.OptimizedEventsHandler;
import com.ssomar.executableevents.executableevents.ExecutableEvent;
import com.ssomar.score.SCore;
import com.ssomar.score.SsomarDev;
import com.ssomar.score.commands.runnable.ActionInfo;
import com.ssomar.score.events.loop.LoopManager;
import com.ssomar.score.features.FeatureAbstract;
import com.ssomar.score.features.FeatureInterface;
import com.ssomar.score.features.FeatureParentInterface;
import com.ssomar.score.features.custom.activators.activator.NewSActivator;
import com.ssomar.score.features.custom.activators.activator.NewSActivatorWithLoopFeature;
import com.ssomar.score.features.custom.commands.block.BlockCommandsFeature;
import com.ssomar.score.features.custom.commands.entity.EntityCommandsFeature;
import com.ssomar.score.features.custom.commands.player.PlayerCommandsFeature;
import com.ssomar.score.features.custom.conditions.block.parent.BlockConditionsFeature;
import com.ssomar.score.features.custom.conditions.entity.parent.EntityConditionsFeature;
import com.ssomar.score.features.custom.conditions.item.parent.ItemConditionsFeature;
import com.ssomar.score.features.custom.conditions.placeholders.group.PlaceholderConditionGroupFeature;
import com.ssomar.score.features.custom.conditions.player.parent.PlayerConditionsFeature;
import com.ssomar.score.features.custom.conditions.world.parent.WorldConditionsFeature;
import com.ssomar.score.features.custom.cooldowns.NewCooldownFeature;
import com.ssomar.score.features.custom.detailedblocks.DetailedBlocks;
import com.ssomar.score.features.custom.detailedslots.DetailedSlots;
import com.ssomar.score.features.custom.loop.LoopFeatures;
import com.ssomar.score.features.custom.required.parent.RequiredGroup;
import com.ssomar.score.features.custom.useperday.UsePerDayFeature;
import com.ssomar.score.features.types.*;
import com.ssomar.score.features.types.list.ListDamageCauseFeature;
import com.ssomar.score.features.types.list.ListDetailedEntityFeature;
import com.ssomar.score.features.types.list.ListUncoloredStringFeature;
import com.ssomar.score.menu.GUI;
import com.ssomar.score.sobject.HigherFormSObject;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.score.sobject.sactivator.SOption;
import com.ssomar.score.splugin.SPlugin;
import com.ssomar.score.utils.*;
import com.ssomar.score.utils.placeholders.StringPlaceholder;
import com.ssomar.sevents.events.player.fish.fish.PlayerFishFishEvent;
import com.ssomar.sevents.events.projectile.hitentity.ProjectileHitEntityEvent;
import com.ssomar.sevents.events.projectile.hitplayer.ProjectileHitPlayerEvent;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.*;

@Getter
@Setter
public class ActivatorEEFeature extends NewSActivator<ActivatorEEFeature, ActivatorEEFeatureEditor, ActivatorEEFeatureEditorManager> implements NewSActivatorWithLoopFeature {

    private final static boolean DEBUG = false;
    private ColoredStringFeature displayName;
    private SOptionFeature optionFeature;
    /**
     * For click activators
     **/
    private TypeTargetFeature typeTargetFeature;
    /**
     * For click on activators
     **/
    private DetailedClickFeature detailedClickFeature;
    private UsePerDayFeature usePerDayFeature;
    private BooleanFeature cancelEvent;
    private BooleanFeature silenceOutput;
    /* MINE & KILL */
    private BooleanFeature desactiveDrops;
    private NewCooldownFeature cooldown;
    private NewCooldownFeature globalCooldown;
    private RequiredGroup requiredGroup;

    private ListDamageCauseFeature detailedDamageCauses;
    private ListUncoloredStringFeature detailedCommands;

    /* Player parts */
    private PlayerCommandsFeature playerCommands;
    private PlayerConditionsFeature playerConditions;

    private PlayerCommandsFeature targetPlayerCommands;
    private PlayerConditionsFeature targetPlayerConditions;

    /* Entity parts */
    private EntityCommandsFeature entityCommands;
    private ListDetailedEntityFeature detailedEntities;
    private EntityConditionsFeature entityConditions;

    private EntityCommandsFeature targetEntityCommands;
    private ListDetailedEntityFeature detailedTargetEntities;
    private EntityConditionsFeature targetEntityConditions;

    /* Block parts */
    private BlockCommandsFeature blockCommands;
    private BlockConditionsFeature blockConditions;
    private DetailedBlocks detailedBlocks;

    private BlockCommandsFeature targetBlockCommands;
    private BlockConditionsFeature targetBlockConditions;
    private DetailedBlocks detailedTargetBlocks;


    private WorldConditionsFeature worldConditions;
    //private ItemConditionsFeature itemConditions;
    private PlaceholderConditionGroupFeature placeholderConditions;
    /**
     * LOOP option only
     **/
    private LoopFeatures loopFeatures;

    public ActivatorEEFeature(FeatureParentInterface parent, String id) {
        super(parent, id);
        reset();
    }

    static void cancelEvent(Event e, boolean condition) {
        if (e != null && condition && e instanceof Cancellable) {
            if (e instanceof ProjectileHitEntityEvent) {
                ((ProjectileHitEntityEvent) e).getEntity().remove();
            } else if (e instanceof ProjectileHitPlayerEvent) {
                ((ProjectileHitPlayerEvent) e).getEntity().remove();
            }

            ((Cancellable) e).setCancelled(true);
        }

    }

    @Override
    public String getParentObjectId() {
        String id = "";
        if (getParent() instanceof FeatureAbstract) {
            FeatureAbstract parent = (FeatureAbstract) getParent();
            int i = 0;
            while (parent instanceof FeatureAbstract) {
                //SsomarDev.testMsg("parent = " + parent.getName()+ " i >> "+i);
                if (i == 5) break;
                if (parent instanceof ExecutableEvent) {
                    id = ((ExecutableEvent) parent).getId();
                    break;
                }
                parent = (FeatureAbstract) parent.getParent();
                i++;
            }
        }
        //SsomarDev.testMsg("getParentObjectId: " + id);
        return id;
    }

    @Override
    public SOption getOption() {
        return optionFeature.getValue();
    }

    @Override
    public void run(HigherFormSObject higherFormSObject, EventInfo eInfo) {
        ExecutableEvent executableEvent = (ExecutableEvent) higherFormSObject;

        if (!DebugMode.getInstance().getPlayersInDebugMode().isEmpty()) {
            for (Player debugP : DebugMode.getInstance().getPlayersInDebugMode()) {
                SendMessage.sendMessageNoPlch(debugP, "§c[DEBUG] &7Activator: &e" + getId() + " &b(run but the check of cdts, cds in coming  1/2) &7of item: &6" + getParentObjectId());
            }
        }
        if (DebugMode.getInstance().isEnabledInConsole()) {
            ExecutableEvents.plugin.getServer().getConsoleSender().sendMessage(StringConverter.coloredString("§c[DEBUG] &7Activator: &e" + getId() + " &b(run but the check of cdts, cds in coming  1/2) &7of item: &6" + getParentObjectId()));
        }

        if (eInfo.getPlayer().isPresent() && eInfo.getPlayer().get().isDead() && !optionFeature.getValue().equals(Option.PLAYER_DEATH) && !optionFeature.getValue().equals(Option.PLAYER_RESPAWN))
            return;

        SsomarDev.testMsg("Activator 0", DEBUG);

        /* Event that activate the activator */
        Event eSrc = eInfo.getEventSource();
        /* Block if there is one */
        Block block = null;
        /* Material of this block when the activator was activated */
        Material oldMaterial = null;
        /* Target Block if there is one */
        Block targetBlock = null;
        /* Material of this block when the activator was activated */
        Material oldMaterialTarget = null;
        /* Entity if there is one */
        Entity entity = null;
        /* Target Entity if there is one */
        Entity targetEntity = null;
        /* Player if there is one */
        Player player = null;
        /* Target Player if there is one */
        Player targetPlayer = null;
        /* World when this event occurs*/
        World world = eInfo.getWorld().orElse(Bukkit.getWorlds().get(0));

        /* Placeholders init */
        StringPlaceholder sp = new StringPlaceholder();
        sp.setActivator(this.getName());
        sp.setMaxUsePerDayActivator(this.usePerDayFeature.getMaxUsePerDay().getValue().get() + "");
        if (eInfo.getBlockface().isPresent()) sp.setBlockface(eInfo.getBlockface().get());
        Optional<Projectile> projOpt = eInfo.getProjectile();
        if (projOpt.isPresent()) {
            SsomarDev.testMsg(" has projectile", DEBUG);
            if (eInfo.getBlockface().isPresent()) sp.setProjectilePlcHldr(projOpt.get(), eInfo.getBlockface().get());
            else sp.setProjectilePlcHldr(projOpt.get(), "");
        }
        if (eInfo.getBowForce().isPresent())
            sp.getExtraPlaceholders().put("%bow_force%", eInfo.getBowForce().get() + "");
        else sp.getExtraPlaceholders().put("%bow_force%", "0");

        /* ActionInfo init */
        ActionInfo actionInfo = new ActionInfo(executableEvent.getDisplayName().getValue().orElse(""), sp);
        actionInfo.setEventCallByMineInCube(eInfo.isEventCallByMineinCube());
        actionInfo.setSilenceOutput(silenceOutput.getValue());
        actionInfo.setVelocity(eInfo.getVelocity());

        SsomarDev.testMsg("Activator 1", DEBUG);

        SsomarDev.testMsg("Activator 2 , main hand ? " + eInfo.isMainHand(), DEBUG);


        /* Verification of the detailedclick for the activators that have this option */
        if (Option.getOptionWithDetailedClick().contains(optionFeature.getValue()) && !this.detailedClickFeature.verifClick(eInfo.getDetailedClick().get()))
            return;

        SsomarDev.testMsg("Activator 2.2", DEBUG);

        /* Verification of isOnlyAir option for the activator that have it */
        if (this.typeTargetFeature.getValue().get().equals(TypeTarget.ONLY_AIR) && eInfo.getDetailedInteraction().isPresent() && !eInfo.getDetailedInteraction().get().equals(DetailedInteraction.AIR))
            return;

        SsomarDev.testMsg("Activator 2.3", DEBUG);

        /* Verification of isOnlyBlock option for the activator that have it */
        if (this.typeTargetFeature.getValue().get().equals(TypeTarget.ONLY_BLOCK) && eInfo.getDetailedInteraction().isPresent() && !eInfo.getDetailedInteraction().get().equals(DetailedInteraction.BLOCK))
            return;

        if (DEBUG) {
            if (eInfo.getDamageCause().isPresent())
                SsomarDev.testMsg("Activator 3 >> &e" + eInfo.getDamageCause().get().name(), DEBUG);
            else SsomarDev.testMsg("Activator 3 >> &cNO CAUSE", DEBUG);
        }

        /* Verification of detailedDamageCause option for the activator that have it */
        if (Option.getOptionWithDetailedDamageCauses().contains(optionFeature.getValue()) && eInfo.getDamageCause().isPresent() && !this.detailedDamageCauses.verifCause(eInfo.getDamageCause().get()))
            return;

        /* Verification of detailedCommands option for the activator that have it */
        if (Option.getOptionWithCommand().contains(optionFeature.getValue())) {

            //SsomarDev.testMsg("Command activator " + eInfo.getCommand());
            String[] split = eInfo.getCommand().get().split(" ");
            int i = 0;
            Map<String, String> map = sp.getExtraPlaceholders();
            for (String arg : split) {
                map.put("%arg" + i + "%", arg);
                i++;
            }

            if (!this.detailedCommands.getValue().isEmpty()) {
                boolean invalid = true;
                for (String s : this.detailedCommands.getValue()) {
                    if (eInfo.getCommand().get().contains(s)) {
                        invalid = false;
                        break;
                    }
                }
                if (invalid) return;
            }
        }

        /* Block init */
        Optional<Block> blockOpt = eInfo.getBlock();
        if (blockOpt.isPresent()) {
            block = blockOpt.get();

            Optional<Material> materialOpt = eInfo.getOldMaterialBlock();
            if (materialOpt.isPresent()) {
                sp.setBlockPlcHldr(block, materialOpt.get());
                oldMaterial = materialOpt.get();
            } else sp.setBlockPlcHldr(block);
        }

        /* Target Block init */
        Optional<Block> targetBlockOpt = eInfo.getTargetBlock();
        if (targetBlockOpt.isPresent()) {
            targetBlock = targetBlockOpt.get();
            actionInfo.setBlock(targetBlock);

            Optional<Material> materialOpt = eInfo.getOldMaterialBlock();
            if (materialOpt.isPresent()) {
                sp.setTargetBlockPlcHldr(targetBlock, materialOpt.get());
                oldMaterialTarget = materialOpt.get();
            } else sp.setTargetBlockPlcHldr(targetBlock);
        }

        SsomarDev.testMsg("Activator 4", DEBUG);

        /* Entity init */
        Optional<Entity> entityOpt = eInfo.getEntity();
        if (entityOpt.isPresent()) {
            entity = entityOpt.get();
            sp.setEntityPlcHldr(entity);
        }

        /* Target entity init */
        Optional<Entity> targetEntityOpt = eInfo.getTargetEntity();
        if (targetEntityOpt.isPresent()) {
            targetEntity = targetEntityOpt.get();
            //SsomarDev.testMsg("Target entity " + targetEntity.getType().name(), true);
            sp.setTargetEntityPlcHldr(targetEntity);
        }

        SsomarDev.testMsg("Activator 4.5", DEBUG);

        /* Player init */
        Optional<Player> playerOpt = eInfo.getPlayer();
        if (playerOpt.isPresent()) {
            player = playerOpt.get();
            sp.setPlayerPlcHldr(player.getUniqueId(), 0);
        }

        /* Target player init */
        Optional<Player> targetPlayerOpt = eInfo.getTargetPlayer();
        if (targetPlayerOpt.isPresent()) {
            targetPlayer = targetPlayerOpt.get();
            sp.setTargetPlcHldr(targetPlayer.getUniqueId());
        }

        SsomarDev.testMsg("Activator 5", DEBUG);

        SendMessage sm = new SendMessage();
        sm.setSp(sp);
        Optional<Player> optionalPlayer = Optional.ofNullable(player);

        /* Verification of the world conditions */
        if (!worldConditions.verifCondition(world, optionalPlayer, sm, eSrc)) return;

        /* Verification of the block conditions */
        if (Option.getOptionWithBlockSt().contains(optionFeature.getValue()) && block != null) {
            if (!detailedBlocks.isValid(block, oldMaterial, eInfo.getOldStatesBlock(), optionalPlayer, eSrc, sp))
                return;

            if (!blockConditions.verifConditions(block, optionalPlayer, sm, eSrc)) return;
        }

        /* Verification of the target block conditions */
        if (Option.getOptionWithTargetBlockSt().contains(optionFeature.getValue()) && targetBlock != null) {
            if (!detailedTargetBlocks.isValid(block ,oldMaterialTarget, eInfo.getOldStatesTargetBlock(), optionalPlayer, eSrc, sp))
                return;

            if (!targetBlockConditions.verifConditions(targetBlock, optionalPlayer, sm, eSrc)) return;
        }

        SsomarDev.testMsg("Activator 6", DEBUG);

        /* Verification of the player conditions */
        if (Option.getOptionWithPlayerSt().contains(optionFeature.getValue()) && player != null) {
            if (!playerConditions.verifCondition(player, optionalPlayer, sm, eSrc)) return;
        }

        /* This part was made for activator that doesnt contain a player, it allows the m to verify the placeholder conditions */
        Player forPlaceholderPlayer = player;
        try {
            if (forPlaceholderPlayer == null)
                forPlaceholderPlayer = Bukkit.getServer().getOnlinePlayers().iterator().next();
        } catch (Exception e) {
            forPlaceholderPlayer = null;
        }
        if(forPlaceholderPlayer != null) {
            //SsomarDev.testMsg(StringPlaceholder.replacePlaceholderOfPAPI(">> %score_variables_IsRoomClearedTeam01%", forPlaceholderPlayer.getUniqueId()), true);
            /* Verification of the placeholders conditions */
            if (!placeholderConditions.verify(forPlaceholderPlayer, targetPlayer, sp, eSrc)) return;
        }

        /* Verification of the target player conditions */
        if (Option.getOptionWithTargetPlayerSt().contains(optionFeature.getValue()) && targetPlayer != null) {
            if (!targetPlayerConditions.verifCondition(targetPlayer, optionalPlayer, sm, eSrc)) return;
        }

        SsomarDev.testMsg("Activator 7", DEBUG);

        /* Verification of the entity conditions */
        if (Option.getOptionWithEntitySt().contains(optionFeature.getValue()) && entity != null) {
            /* Verification of the detailedEntities option  */
            if (!detailedEntities.isValidEntity(entity)) return;

            if (!entityConditions.verifConditions(entity, optionalPlayer, sm, eSrc)) return;
        }

        /* Verification of the target entity conditions */
        if (Option.getOptionWithTargetEntitySt().contains(optionFeature.getValue()) && targetEntity != null) {
            /* Verification of the detailedEntities option  */
            if (!detailedTargetEntities.isValidEntity(targetEntity)) return;

            if (!targetEntityConditions.verifConditions(targetEntity, optionalPlayer, sm, eSrc)) return;
        }

        SsomarDev.testMsg("Activator 7.5 player null ? "+(player == null), DEBUG);

        if (Option.getOptionWithPlayerSt().contains(optionFeature.getValue()) && player != null) {
            SsomarDev.testMsg("Activator 8", DEBUG);
            /* Verification of the required ExecutableItems option  */
            if (!requiredGroup.verify(player, eSrc)) return;

            /* Verif use per day for the activator  */ // TODO can be adapted to entities
            if (!this.usePerDayFeature.verify(player, eSrc, sp)) return;

            SsomarDev.testMsg("Activator 8.1", DEBUG);

            /* Check if the activator is in cooldown for the player or not  */
            if (!globalCooldown.checkCooldown(player, eSrc, sp, executableEvent, true)) return;
            if (!cooldown.checkCooldown(player, eSrc, sp, executableEvent, false)) return;
            SsomarDev.testMsg("Activator 8.2", DEBUG);
        }

        if (Option.getOptionWithEntitySt().contains(optionFeature.getValue()) && entity != null) {
            /* Check if the activator is in cooldown for the player or not  */
            if (!globalCooldown.checkCooldown(entity, eSrc, sp, executableEvent, true)) return;
            if (!cooldown.checkCooldown(entity, eSrc, sp, executableEvent, false)) return;
        }

        SsomarDev.testMsg("Activator 9", DEBUG);

        /* Feature to cancel an activator before his complete activation */
        if (eInfo.isMustDoNothing()) {
            cancelEvent(eSrc, cancelEvent.getValue());
            return;
        }

        SsomarDev.testMsg("Activator 10", DEBUG);


        /* HERE we finish to check all conditions, the activator can be executed */

        if (!DebugMode.getInstance().getPlayersInDebugMode().isEmpty()) {
            for (Player debugP : DebugMode.getInstance().getPlayersInDebugMode()) {
                actionInfo.getDebugers().add(debugP);
                SendMessage.sendMessageNoPlch(debugP, "§c[DEBUG] &7Activator: &e" + getId() + " &aACTIVATED 2/2 &7of item: &6" + getParentObjectId());
            }
        }
        if (DebugMode.getInstance().isEnabledInConsole()) {
            actionInfo.getDebugers().add(ExecutableEvents.plugin.getServer().getConsoleSender());
            ExecutableEvents.plugin.getServer().getConsoleSender().sendMessage(StringConverter.coloredString("§c[DEBUG] &7Activator: &e" + getId() + " &aACTIVATED 2/2 &7of item: &6" + getParentObjectId()));
        }


        if (Option.getOptionWithPlayerSt().contains(optionFeature.getValue()) && player != null) {
            /* Add cooldown */ //
            cooldown.addCooldown(player, executableEvent);
            globalCooldown.addGlobalCooldown(executableEvent);

            /* Take required Things */ // TODO can be adapted to entities
            requiredGroup.take(player);

        }

        if (Option.getOptionWithEntitySt().contains(optionFeature.getValue()) && entity != null) {
            /* Add cooldown */
            cooldown.addCooldown(entity, executableEvent);
            globalCooldown.addGlobalCooldown(executableEvent);
        }

        /* Cancel event if the option to cancel it is activated */
        SsomarDev.testMsg("Activator 10.5 cancelEvent: ? " + cancelEvent.getValue(), DEBUG);

        cancelEvent(eSrc, cancelEvent.getValue());

        if (desactiveDrops.getValue() && !SCore.is1v11Less()) {
            if (eSrc instanceof BlockBreakEvent) {
                BlockBreakEvent bBE = (BlockBreakEvent) eSrc;
                bBE.setDropItems(false);
                bBE.setExpToDrop(0);
            } else if (eSrc instanceof PlayerFishFishEvent) {
                PlayerFishFishEvent pFE = (PlayerFishFishEvent) eSrc;
                pFE.setAmount(0);
                pFE.getCaught().remove();
            } else if (eSrc instanceof EntityDeathEvent) {
                EntityDeathEvent eDE = (EntityDeathEvent) eSrc;
                eDE.setDroppedExp(0);
                eDE.getDrops().clear();
            } else if (eSrc instanceof PlayerDeathEvent) {
                PlayerDeathEvent pDE = (PlayerDeathEvent) eSrc;
                pDE.setDroppedExp(0);
                pDE.getDrops().clear();
            }
        }

        SsomarDev.testMsg("Activator 11", DEBUG);


        /* Finally we run all commands */

        if (Option.getOptionWithPlayerSt().contains(optionFeature.getValue()) && player != null) {
            actionInfo.setReceiverUUID(player.getUniqueId());
            actionInfo.setLauncherUUID(player.getUniqueId());
            playerCommands.runCommands(actionInfo, executableEvent.getDisplayName().getValue().orElse(""));
        }

        if (Option.getOptionWithBlockSt().contains(optionFeature.getValue()) && block != null) {
            actionInfo.setBlock(block);
            actionInfo.setOldBlockMaterialName(oldMaterial.toString());
            blockCommands.runCommands(actionInfo, executableEvent.getDisplayName().getValue().orElse(""));
        }

        if (Option.getOptionWithEntitySt().contains(optionFeature.getValue()) && entity != null) {
            actionInfo.setEntityUUID(entity.getUniqueId());
            entityCommands.runCommands(actionInfo, executableEvent.getDisplayName().getValue().orElse(""));
        }

        /* Verification of the target player conditions */
        if (Option.getOptionWithTargetPlayerSt().contains(optionFeature.getValue()) && targetPlayer != null) {
            /* change actionInfo */
            ActionInfo targetAInfo = actionInfo.clone();
            targetAInfo.setReceiverUUID(targetPlayer.getUniqueId());
            targetPlayerCommands.runCommands(targetAInfo, executableEvent.getDisplayName().getValue().orElse(""));
        }

        if (Option.getOptionWithTargetBlockSt().contains(optionFeature.getValue()) && targetBlock != null) {
            ActionInfo aIClone = actionInfo.clone();
            aIClone.setBlock(targetBlock);
            aIClone.setOldBlockMaterialName(oldMaterialTarget.toString());
            targetBlockCommands.runCommands(aIClone, executableEvent.getDisplayName().getValue().orElse(""));
        }

        /* Verification of the target entity conditions */
        if (Option.getOptionWithTargetEntitySt().contains(optionFeature.getValue()) && targetEntity != null) {
            ActionInfo aIClone = actionInfo.clone();
            aIClone.setEntityUUID(targetEntity.getUniqueId());
            targetEntityCommands.runCommands(aIClone, executableEvent.getDisplayName().getValue().orElse(""));
        }


        SsomarDev.testMsg("Activator 12", DEBUG);

        if (Option.getOptionWithPlayerSt().contains(optionFeature.getValue()) && player != null) {
            this.usePerDayFeature.incrementUsage(player);
        }
    }

    @Override
    public List<String> getMenuDescription() {
        return null;
    }

    @Override
    public NewSActivator getBuilderInstance(FeatureParentInterface featureParentInterface, String s) {
        NewSActivator newSActivator = new ActivatorEEFeature(featureParentInterface, s);
        return newSActivator;
    }

    @Override
    public List<FeatureInterface> getFeatures() {
        List<FeatureInterface> features = new ArrayList<FeatureInterface>(Arrays.asList(displayName, optionFeature));

        if (Option.getOptionWithOnlyTypeClick().contains(optionFeature.getValue())) {
            features.add(typeTargetFeature);
        }

        if (Option.getOptionWithDetailedClick().contains(optionFeature.getValue())) {
            features.add(detailedClickFeature);
        }

        if (Option.getOptionWithDrops().contains(optionFeature.getValue())) {
            features.add(desactiveDrops);
        }

        features.addAll(Arrays.asList(usePerDayFeature, cancelEvent, silenceOutput, cooldown, globalCooldown, requiredGroup));

        features.addAll(Arrays.asList(worldConditions, placeholderConditions));

        if (optionFeature.getValue().equals(Option.LOOP)) {
            features.add(loopFeatures);
        }

        if (Option.getOptionWithDetailedDamageCauses().contains(optionFeature.getValue())) {
            features.add(detailedDamageCauses);
        }

        if (Option.getOptionWithCommand().contains(optionFeature.getValue())) {
            features.add(detailedCommands);
        }

        if (Option.getOptionWithPlayerSt().contains(optionFeature.getValue())) {
            features.add(playerConditions);
            features.add(playerCommands);
        }

        if (Option.getOptionWithTargetPlayerSt().contains(optionFeature.getValue())) {
            features.add(targetPlayerCommands);
            features.add(targetPlayerConditions);
        }

        if (Option.getOptionWithEntitySt().contains(optionFeature.getValue())) {
            features.add(entityConditions);
            features.add(entityCommands);
            features.add(detailedEntities);
        }

        if (Option.getOptionWithTargetEntitySt().contains(optionFeature.getValue())) {
            features.add(detailedTargetEntities);
            features.add(targetEntityCommands);
            features.add(targetEntityConditions);
        }

        if (Option.getOptionWithBlockSt().contains(optionFeature.getValue())) {
            features.add(detailedBlocks);
            features.add(blockCommands);
            features.add(blockConditions);
        }

        if ((!Option.getOptionWithOnlyTypeClick().contains(optionFeature.getValue()) && Option.getOptionWithTargetBlockSt().contains(optionFeature.getValue())) ||
                (Option.getOptionWithOnlyTypeClick().contains(optionFeature.getValue())) && typeTargetFeature.getValue().get().equals(TypeTarget.ONLY_BLOCK)) {
            features.add(detailedTargetBlocks);
            features.add(targetBlockCommands);
            features.add(targetBlockConditions);
        }

        return features;
    }

    @Override
    public String getParentInfo() {
        return getParent().getParentInfo();
    }

    @Override
    public ConfigurationSection getConfigurationSection() {
        ConfigurationSection parentSection = getParent().getConfigurationSection();
        if (parentSection.isConfigurationSection(getId())) {
            return parentSection.getConfigurationSection(getId());
        } else return parentSection.createSection(getId());
    }

    @Override
    public File getFile() {
        return getParent().getFile();
    }

    @Override
    public void reload() {
        SsomarDev.testMsg("Reloading activator " + getId(), DEBUG);
        SsomarDev.testMsg("RELOAD INST >> " + getParent().hashCode(), DEBUG);
        for (FeatureInterface feature : getParent().getFeatures()) {
            if (feature instanceof ActivatorEEFeature) {
                ActivatorEEFeature a = (ActivatorEEFeature) feature;
                //SsomarDev.testMsg("activator >>> " + a.getId());
                if (a.getId().equals(getId())) {
                    a.setDisplayName(displayName);
                    SsomarDev.testMsg("Reloading activator " + getId() + " option: " + optionFeature.getValue().toString(), DEBUG);
                    a.setOptionFeature(optionFeature);
                    a.setTypeTargetFeature(typeTargetFeature);
                    a.setDetailedClickFeature(detailedClickFeature);
                    a.setUsePerDayFeature(usePerDayFeature);
                    a.setCancelEvent(cancelEvent);
                    a.setSilenceOutput(silenceOutput);
                    a.setDesactiveDrops(desactiveDrops);
                    a.setCooldown(cooldown);
                    a.setGlobalCooldown(globalCooldown);
                    a.setRequiredGroup(requiredGroup);
                    a.setLoopFeatures(loopFeatures);
                    a.setDetailedBlocks(detailedBlocks);
                    a.setDetailedTargetBlocks(detailedTargetBlocks);
                    a.setDetailedEntities(detailedEntities);
                    a.setDetailedTargetEntities(detailedTargetEntities);
                    a.setDetailedDamageCauses(detailedDamageCauses);
                    a.setDetailedCommands(detailedCommands);

                    a.setPlayerCommands(playerCommands);
                    a.setTargetPlayerCommands(targetPlayerCommands);

                    a.setEntityCommands(entityCommands);
                    a.setTargetEntityCommands(targetEntityCommands);

                    a.setBlockCommands(blockCommands);
                    a.setTargetBlockCommands(targetBlockCommands);

                    a.setPlayerConditions(playerConditions);
                    a.setTargetPlayerConditions(targetPlayerConditions);

                    a.setBlockConditions(blockConditions);
                    a.setTargetBlockConditions(targetBlockConditions);

                    a.setEntityConditions(entityConditions);
                    a.setTargetEntityConditions(targetEntityConditions);

                    a.setWorldConditions(worldConditions);
                    //a.setItemConditions(itemConditions);
                    a.setPlaceholderConditions(placeholderConditions);
                    break;
                }
            }
        }
    }

    @Override
    public ActivatorEEFeature clone(FeatureParentInterface newParent) {
        ActivatorEEFeature clone = new ActivatorEEFeature(newParent, getId());
        clone.setDisplayName(displayName.clone(clone));
        clone.setOptionFeature(optionFeature.clone(clone));
        clone.setTypeTargetFeature(typeTargetFeature.clone(clone));
        clone.setDetailedClickFeature(detailedClickFeature.clone(clone));
        clone.setUsePerDayFeature(usePerDayFeature.clone(clone));
        clone.setCancelEvent(cancelEvent.clone(clone));
        clone.setSilenceOutput(silenceOutput.clone(clone));
        clone.setDesactiveDrops(desactiveDrops.clone(clone));
        clone.setCooldown(cooldown.clone(clone));
        clone.setGlobalCooldown(globalCooldown.clone(clone));
        clone.setRequiredGroup(requiredGroup.clone(clone));
        clone.setLoopFeatures(loopFeatures.clone(clone));

        clone.setDetailedBlocks(detailedBlocks.clone(clone));
        clone.setDetailedTargetBlocks(detailedTargetBlocks.clone(clone));

        clone.setDetailedEntities(detailedEntities.clone(clone));
        clone.setDetailedTargetEntities(detailedTargetEntities.clone(clone));

        clone.setDetailedDamageCauses(detailedDamageCauses.clone(clone));
        clone.setDetailedCommands(detailedCommands.clone(clone));

        clone.setPlayerCommands(playerCommands.clone(clone));
        clone.setTargetPlayerCommands(targetPlayerCommands.clone(clone));

        clone.setEntityCommands(entityCommands.clone(clone));
        clone.setTargetEntityCommands(targetEntityCommands.clone(clone));

        clone.setBlockCommands(blockCommands.clone(clone));
        clone.setTargetBlockCommands(targetBlockCommands.clone(clone));

        clone.setPlayerConditions(playerConditions.clone(clone));
        clone.setTargetPlayerConditions(targetPlayerConditions.clone(clone));

        clone.setBlockConditions(blockConditions.clone(clone));
        clone.setTargetBlockConditions(targetBlockConditions.clone(clone));

        clone.setEntityConditions(entityConditions.clone(clone));
        clone.setTargetEntityConditions(targetEntityConditions.clone(clone));

        clone.setWorldConditions(worldConditions.clone(clone));
        //clone.setItemConditions(itemConditions.clone(clone));
        clone.setPlaceholderConditions(placeholderConditions.clone(clone));
        return clone;
    }

    @Override
    public void openEditor(@NotNull Player player) {
        ActivatorEEFeatureEditorManager.getInstance().startEditing(player, this);
    }

    @Override
    public void openBackEditor(@NotNull Player player) {
        getParent().openEditor(player);
    }

    @Override
    public List<String> load(SPlugin sPlugin, ConfigurationSection config, boolean premiumLoading) {
        List<String> errors = new ArrayList<>();
        if (config.isConfigurationSection(getId())) {
            ConfigurationSection section = config.getConfigurationSection(getId());

            for (FeatureInterface f : getFeatures()) {
                errors.addAll(f.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithOnlyTypeClick().contains(optionFeature.getValue())) {
                errors.addAll(typeTargetFeature.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithDetailedClick().contains(optionFeature.getValue())) {
                errors.addAll(detailedClickFeature.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithDrops().contains(optionFeature.getValue())) {
                errors.addAll(desactiveDrops.load(sPlugin, section, premiumLoading));
            }

            if (optionFeature.getValue().equals(Option.LOOP)) {
                errors.addAll(loopFeatures.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithDetailedDamageCauses().contains(optionFeature.getValue())) {
                errors.addAll(detailedDamageCauses.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithCommand().contains(optionFeature.getValue())) {
                errors.addAll(detailedCommands.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithCommand().contains(optionFeature.getValue())) {
                errors.addAll(playerCommands.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithPlayerSt().contains(optionFeature.getValue())) {
                errors.addAll(playerCommands.load(sPlugin, section, premiumLoading));
                errors.addAll(playerConditions.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithTargetPlayerSt().contains(optionFeature.getValue())) {
                errors.addAll(targetPlayerCommands.load(sPlugin, section, premiumLoading));
                errors.addAll(targetPlayerConditions.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithEntitySt().contains(optionFeature.getValue())) {
                errors.addAll(entityCommands.load(sPlugin, section, premiumLoading));
                errors.addAll(entityConditions.load(sPlugin, section, premiumLoading));
                errors.addAll(detailedEntities.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithTargetEntitySt().contains(optionFeature.getValue())) {
                errors.addAll(targetEntityCommands.load(sPlugin, section, premiumLoading));
                errors.addAll(targetEntityConditions.load(sPlugin, section, premiumLoading));
                errors.addAll(detailedTargetEntities.load(sPlugin, section, premiumLoading));
            }

            if (Option.getOptionWithBlockSt().contains(optionFeature.getValue())) {
                errors.addAll(blockCommands.load(sPlugin, section, premiumLoading));
                errors.addAll(blockConditions.load(sPlugin, section, premiumLoading));
                errors.addAll(detailedBlocks.load(sPlugin, section, premiumLoading));
            }

            if ((!Option.getOptionWithOnlyTypeClick().contains(optionFeature.getValue()) && Option.getOptionWithTargetBlockSt().contains(optionFeature.getValue())) ||
                    (Option.getOptionWithOnlyTypeClick().contains(optionFeature.getValue())) && typeTargetFeature.getValue().get().equals(TypeTarget.ONLY_BLOCK)) {
                errors.addAll(targetBlockCommands.load(sPlugin, section, premiumLoading));
                errors.addAll(targetBlockConditions.load(sPlugin, section, premiumLoading));
                errors.addAll(detailedTargetBlocks.load(sPlugin, section, premiumLoading));
            }

        }
        return errors;
    }

    @Override
    public void save(ConfigurationSection config) {
        ConfigurationSection attributeConfig = config.createSection(getId());

        /* Add the activator to the LOOP Manager if its a LOOP activator and if it is not present (new activator) */
        if (optionFeature.getValue().equals(Option.LOOP)) {
            SsomarDev.testMsg("delay >> " + loopFeatures.getDelay().getValue().get(), DEBUG);
            LoopManager.getInstance().addLoopActivator(this);
        } else LoopManager.getInstance().removeLoopActivator(this);

        OptimizedEventsHandler.getInstance().read((Option) optionFeature.getValue());

        for (FeatureInterface feature : getFeatures()) {
            feature.save(attributeConfig);
        }
    }

    @Override
    public ActivatorEEFeature getValue() {
        return this;
    }

    @Override
    public ActivatorEEFeature initItemParentEditor(GUI gui, int slot) {
        String[] finalDescription = new String[getEditorDescription().length + 3];
        System.arraycopy(getEditorDescription(), 0, finalDescription, 0, getEditorDescription().length);
        if (displayName.getValue().isPresent())
            finalDescription[finalDescription.length - 3] = "§7Name: §e" + displayName.getValue().get();
        else finalDescription[finalDescription.length - 3] = "§7Name: §cNot set";
        finalDescription[finalDescription.length - 2] = "&7Option: &e" + optionFeature.getValue();
        finalDescription[finalDescription.length - 1] = gui.CLICK_HERE_TO_CHANGE;

        gui.createItem(getEditorMaterial(), 1, slot, gui.TITLE_COLOR + getEditorName() + " - " + "(" + getId() + ")", false, false, finalDescription);
        return this;
    }

    @Override
    public void updateItemParentEditor(GUI gui) {

    }

    @Override
    public boolean isTheFeatureClickedParentEditor(String featureClicked) {
        return featureClicked.contains(getEditorName()) && featureClicked.contains("(" + getId() + ")");
    }

    @Override
    public void reset() {
        this.displayName = new ColoredStringFeature(this, "name", Optional.of("&eActivator"), "DisplayName", new String[]{"&7&oThe display name"}, GUI.WRITABLE_BOOK, false, false);
        this.optionFeature = new SOptionFeature(ExecutableEvents.plugin, Option.PLAYER_ALL_CLICK, this, "option", "Option", new String[]{"&7&oThe Option"}, Material.COMPASS, false);

        this.typeTargetFeature = new TypeTargetFeature(this, "typeTarget", Optional.of(TypeTarget.NO_TYPE_TARGET), "Type Target", new String[]{"&7&oThe type of target"}, Material.COMPASS, false);

        this.detailedClickFeature = new DetailedClickFeature(this, "detailedClick", Optional.of(DetailedClick.LEFT), "Detailed Click", new String[]{"&7&oThe specific click"}, Material.COMPASS, false);

        this.usePerDayFeature = new UsePerDayFeature(this, getParentObjectId() + ">" + getId());

        this.cancelEvent = new BooleanFeature(this, "cancelEvent", false, "Cancel Event", new String[]{"&7&oCancel the vanilla event"}, Material.LEVER, false, false);
        this.silenceOutput = new BooleanFeature(this, "silenceOutput", false, "Silence Output", new String[]{"&7&oSilence the output of the commands", "&7&o(Only in the console)"}, Material.LEVER, false, false);
        this.desactiveDrops = new BooleanFeature(this, "desactiveDrops", false, "Desactive Drops", new String[]{"&7&oDesactive the drops"}, Material.LEVER, false, true);

        this.cooldown = new NewCooldownFeature(this, "cooldownOptions", "Cooldown Options", new String[]{"&7&oThe cooldown"}, GUI.CLOCK, false, ExecutableEvents.plugin, GeneralConfig.getInstance().isPremiumEnableCooldown());

        this.globalCooldown = new NewCooldownFeature(this, "globalCooldownOptions", "Global Cooldown Options", new String[]{"&7&oThe global cooldown", "&7&o(For &eALL &7&oplayers)"}, GUI.CLOCK, false, ExecutableEvents.plugin, GeneralConfig.getInstance().isPremiumEnableCooldown());

        this.requiredGroup = new RequiredGroup(this);

        this.loopFeatures = new LoopFeatures(this);

        this.detailedBlocks = new DetailedBlocks(this);

        this.detailedTargetBlocks = new DetailedBlocks(this, "detailedTargetBlocks", "Detailed Target Blocks");

        this.detailedEntities = new ListDetailedEntityFeature(this, "detailedEntities", new ArrayList<>(), "Detailed Entities", new String[]{"&7&oSpecify a list of entities that", "&7&ocan be affected", "&7&oempty = all entities"}, FixedMaterial.getMaterial(Arrays.asList("ZOMBIE_HEAD", "MONSTER_EGG")), false, false);

        this.detailedTargetEntities = new ListDetailedEntityFeature(this, "detailedTargetEntities", new ArrayList<>(), "Detailed Target Entities", new String[]{"&7&oSpecify a list of entities that", "&7&ocan be affected", "&7&oempty = all entities"}, FixedMaterial.getMaterial(Arrays.asList("ZOMBIE_HEAD", "MONSTER_EGG")), false, false);

        this.detailedDamageCauses = new ListDamageCauseFeature(this, "detailedDamageCauses", new ArrayList<>(), "Detailed DamageCauses", new String[]{"&7&oSpecify a list of damageCauses that", "&7&ocan be affected", "&7&oempty = all causes"}, Material.BONE, false, false);

        this.detailedCommands = new ListUncoloredStringFeature(this, "detailedCommands", new ArrayList<>(), "Detailed Commands", new String[]{"&7&oSpecify a list of commands that", "&7&ocan be affected", "&7&oempty = no command", "&7Example: &agamemode creative"}, GUI.WRITABLE_BOOK, false, false, Optional.empty());

        this.playerCommands = new PlayerCommandsFeature(this, "commands", "Player Commands", new String[]{"&7&oThe player commands to execute"}, FixedMaterial.getMaterial(Arrays.asList("COMMAND_BLOCK", "COMMAND")), false);

        this.targetPlayerCommands = new PlayerCommandsFeature(this, "targetPlayerCommands", "Target Player Commands", new String[]{"&7&oThe target commands to execute"}, FixedMaterial.getMaterial(Arrays.asList("COMMAND_BLOCK", "COMMAND")), false);

        this.entityCommands = new EntityCommandsFeature(this, "entityCommands", "Entity Commands", new String[]{"&7&oThe entity commands to execute"}, FixedMaterial.getMaterial(Arrays.asList("COMMAND_BLOCK", "COMMAND")), false);

        this.targetEntityCommands = new EntityCommandsFeature(this, "targetEntityCommands", "Target Entity Commands", new String[]{"&7&oThe entity commands to execute"}, FixedMaterial.getMaterial(Arrays.asList("COMMAND_BLOCK", "COMMAND")), false);

        this.blockCommands = new BlockCommandsFeature(this, "blockCommands", "Block Commands", new String[]{"&7&oThe block commands to execute"}, FixedMaterial.getMaterial(Arrays.asList("COMMAND_BLOCK", "COMMAND")), false);

        this.targetBlockCommands = new BlockCommandsFeature(this, "targetBlockCommands", "Target Block Commands", new String[]{"&7&oThe block commands to execute"}, FixedMaterial.getMaterial(Arrays.asList("COMMAND_BLOCK", "COMMAND")), false);

        this.playerConditions = new PlayerConditionsFeature(this, "playerConditions", "Player Conditions", new String[]{});

        this.targetPlayerConditions = new PlayerConditionsFeature(this, "targetPlayerConditions", "Target Player Conditions", new String[]{});

        this.blockConditions = new BlockConditionsFeature(this, "blockConditions", "Block Conditions", new String[]{});

        this.targetBlockConditions = new BlockConditionsFeature(this, "targetBlockConditions", "Target Block Conditions", new String[]{});

        this.entityConditions = new EntityConditionsFeature(this, "entityConditions", "Entity Conditions", new String[]{});

        this.targetEntityConditions = new EntityConditionsFeature(this, "targetEntityConditions", "Target Entity Conditions", new String[]{});

        this.worldConditions = new WorldConditionsFeature(this, "worldConditions", "World Conditions", new String[]{});

       // this.itemConditions = new ItemConditionsFeature(this, "itemConditions", "Item Conditions", new String[]{});

        this.placeholderConditions = new PlaceholderConditionGroupFeature(this);
    }
}
